package OpenAssets.demo.Services;

import OpenAssets.demo.DTOs.VectorDTO;
import OpenAssets.demo.Entities.VectorEntity;
import OpenAssets.demo.Repositories.VectorsRepo;
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
public class VectorServices {

    private final VectorsRepo vectorsRepo;
    @Value("${app.storage.path}")
    private String SAVE_LOCATION;
    private final ServletContext servletContext;

    public VectorServices(VectorsRepo vectorsRepo, ServletContext servletContext) {
        this.vectorsRepo = vectorsRepo;
        this.servletContext = servletContext;
    }


    public List<VectorDTO> findBySearchTerm(String term) {
        List<VectorEntity> allVectors = vectorsRepo.findBySearchTerm(term);
        return allVectors.stream()
                .map(vector -> {
                    String previewImageUrl = "http://localhost:8080" + "/files/" + vector.getPreviewPath();
                    return new VectorDTO(vector.getVectorId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }

    public VectorEntity uploadFiles(String title, String description, String creator, String keywords, MultipartFile previewImage, MultipartFile file) throws IOException {

        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String uniquePreviewName = UUID.randomUUID().toString() + "_" + previewImage.getOriginalFilename();

        String vectorFileSubDir = "vectors_files";
        String previewImageSubDir = "preview_images";

        Path vectorUploadDir = Paths.get(SAVE_LOCATION, vectorFileSubDir);
        Path previewUploadDir = Paths.get(SAVE_LOCATION, previewImageSubDir);

        Path targetFilePath = vectorUploadDir.resolve(uniqueFileName);
        Path targetPreviewPath = previewUploadDir.resolve(uniquePreviewName);

        Files.copy(file.getInputStream(), targetFilePath);
        Files.copy(previewImage.getInputStream(), targetPreviewPath);

        String relativeFilePath = Paths.get(vectorFileSubDir, uniqueFileName).toString();
        String relativePreviewPath = Paths.get(previewImageSubDir, uniquePreviewName).toString();

        VectorEntity newVector = new VectorEntity(title, description, creator, keywords, relativePreviewPath, relativeFilePath);

        return vectorsRepo.save(newVector);

    }

    public ResponseEntity<Resource> downloadFile(Integer id) throws MalformedURLException {
        String relativePath = vectorsRepo.findByVectorId(id).getFilePath().replace('\\', '/');
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

    public List<VectorDTO> getAllPreviews() {
        List<VectorEntity> allVectors = vectorsRepo.findAll();
        return allVectors.stream()
                .map(vector -> {
                    String previewImageUrl = "http://localhost:8080" + "/files/" + vector.getPreviewPath();
                    return new VectorDTO(vector.getVectorId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }
}
