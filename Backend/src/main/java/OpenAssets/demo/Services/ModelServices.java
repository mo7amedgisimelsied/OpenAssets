package OpenAssets.demo.Services;

import OpenAssets.demo.DTOs.ModelDTO;
import OpenAssets.demo.DTOs.VectorDTO;
import OpenAssets.demo.Entities.ModelEntity;
import OpenAssets.demo.Entities.VectorEntity;
import OpenAssets.demo.Repositories.ModelRepo;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServices {

    private final ModelRepo modelRepo;

    public ModelServices(ModelRepo modelRepo, ServletContext servletContext) {
        this.modelRepo = modelRepo;
        this.servletContext = servletContext;
    }

    @Value("${app.storage.path}")
    private String SAVE_LOCATION;
    private final ServletContext servletContext;

    public List<ModelDTO> findBySearchTerm(String term) {
        List<ModelEntity> allVectors = modelRepo.findBySearchTerm(term);
        return allVectors.stream()
                .map(model -> {
                    String previewImageUrl = "http://localhost:8080" + "/files/" + model.getPreviewPath();
                    return new ModelDTO(model.getModelId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }

    public ModelEntity uploadFiles(String title, String description, String creator, String keywords, MultipartFile previewImage, MultipartFile file) throws IOException {

        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String uniquePreviewName = UUID.randomUUID().toString() + "_" + previewImage.getOriginalFilename();

        String modelsFileSubDir = "models_files";
        String previewImageSubDir = "preview_images";

        Path modelsUploadDir = Paths.get(SAVE_LOCATION, modelsFileSubDir);
        Path previewUploadDir = Paths.get(SAVE_LOCATION, previewImageSubDir);

        Path targetFilePath = modelsUploadDir.resolve(uniqueFileName);
        Path targetPreviewPath = previewUploadDir.resolve(uniquePreviewName);

        Files.copy(file.getInputStream(), targetFilePath);
        Files.copy(previewImage.getInputStream(), targetPreviewPath);

        String relativeFilePath = Paths.get(modelsFileSubDir, uniqueFileName).toString();
        String relativePreviewPath = Paths.get(previewImageSubDir, uniquePreviewName).toString();

        ModelEntity newModel = new ModelEntity(title, description, creator, keywords, relativePreviewPath, relativeFilePath);
        return modelRepo.save(newModel);
    }


    public ResponseEntity<Resource> downloadFile(Integer id) throws MalformedURLException {
        String relativePath = modelRepo.findByModelId(id).getFilePath().replace('\\', '/');
        Path filePath = Paths.get(SAVE_LOCATION).resolve(relativePath).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                String mimeType = servletContext.getMimeType(resource.getFile().getAbsolutePath());
                String contentType = (mimeType != null) ? mimeType : "application/octet-stream";

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModelDTO> getAllPreviews() {
        List<ModelEntity> allVectors = modelRepo.findAll();
        return allVectors.stream()
                .map(model -> {
                    String previewImageUrl = "http://localhost:8080" + "/files/" + model.getPreviewPath();
                    return new ModelDTO(model.getModelId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }

}
