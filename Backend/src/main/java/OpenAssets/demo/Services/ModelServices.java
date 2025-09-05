package OpenAssets.demo.Services;

import OpenAssets.demo.DTOs.ModelDTO;
import OpenAssets.demo.Entities.ModelEntity;
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

/// Service class for handling model-related logic
@Service
public class ModelServices {

    /// Repository for accessing model data
    private final ModelRepo modelRepo;
    
    /// Location to save uploaded files, injected from application properties
    @Value("${app.storage.path}")
    private String SAVE_LOCATION;

    /// Servlet context for determining MIME types
    private final ServletContext servletContext;

    /// Constructor for dependency injection
    public ModelServices(ModelRepo modelRepo, ServletContext servletContext) {
        this.modelRepo = modelRepo;
        this.servletContext = servletContext;
    }

    /// Searches for models matching the given search term
    public List<ModelDTO> findBySearchTerm(String term) {
        List<ModelEntity> allModels = modelRepo.findBySearchTerm(term);
        return allModels.stream()
                .map(model -> {
                    /// Construct the full URL for the preview image
                    String previewImageUrl = "http://localhost:8080" + "/files/" + model.getPreviewPath();
                    return new ModelDTO(model.getModelId(), previewImageUrl);
                })
                /// merge the results into a list
                .collect(Collectors.toList());
    }


    /// Handles uploading of model files and their associated preview images to the database
    public ModelEntity uploadFiles(String title, String description, String creator, String keywords, MultipartFile previewImage, MultipartFile file) throws IOException {

        /// Generate unique filenames to avoid collisions
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String uniquePreviewName = UUID.randomUUID().toString() + "_" + previewImage.getOriginalFilename();

        /// Define subdirectories for models and preview images
        String modelsFileSubDir = "models_files";
        String previewImageSubDir = "preview_images";

        /// construct the directory paths in which the files will be saved
        Path modelsUploadDir = Paths.get(SAVE_LOCATION, modelsFileSubDir);
        Path previewUploadDir = Paths.get(SAVE_LOCATION, previewImageSubDir);

        //// construct the full paths for saving the files
        Path targetFilePath = modelsUploadDir.resolve(uniqueFileName);
        Path targetPreviewPath = previewUploadDir.resolve(uniquePreviewName);

        /// copy the model file from the request to the target directory
        Files.copy(file.getInputStream(), targetFilePath);

        /// copy the preview image from the request to the target directory
        Files.copy(previewImage.getInputStream(), targetPreviewPath);

        /// construct the relative file paths for to be saved in the database
        String relativeFilePath = Paths.get(modelsFileSubDir, uniqueFileName).toString();
        String relativePreviewPath = Paths.get(previewImageSubDir, uniquePreviewName).toString();

        /// finally, create the new model entity and save it to the database
        ModelEntity newModel = new ModelEntity(title, description, creator, keywords, relativePreviewPath, relativeFilePath);
        return modelRepo.save(newModel);
    }


    /// Handles downloading of model files
    public ResponseEntity<Resource> downloadFile(Integer id) throws MalformedURLException {
        /// Get the relative file path from the database and replace backslashes with forward slashes for compatibility with Docker
        String relativePath = modelRepo.findByModelId(id).getFilePath().replace('\\', '/');
        
        /// construct the full path to the file to be downloaded
        Path filePath = Paths.get(SAVE_LOCATION).resolve(relativePath).normalize();

        try {
            /// create a resource object from the file path
            Resource resource = new UrlResource(filePath.toUri());

            /// check if the resource exists and is readable
            if (resource.exists() && resource.isReadable()) {
                /// determine the MIME type of the file and set the content type accordingly
                String mimeType = servletContext.getMimeType(resource.getFile().getAbsolutePath());
                String contentType = (mimeType != null) ? mimeType : "application/octet-stream";

                /// return the file as a response
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } 
            /// if the file does not exist or is not readable, return a 404 response
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// Retrieves all model previews from the database
    public List<ModelDTO> getAllPreviews() {
        List<ModelEntity> allModels = modelRepo.findAll();
        return allModels.stream()
                .map(model -> {
                    /// Construct the full URL for the preview image
                    String previewImageUrl = "http://localhost:8080" + "/files/" + model.getPreviewPath();
                    return new ModelDTO(model.getModelId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }
}