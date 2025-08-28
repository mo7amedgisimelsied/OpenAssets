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

/// Service class for handling vector-related logic
@Service
public class VectorServices {

    /// Repository for accessing vector data
    private final VectorsRepo vectorsRepo;

    /// Location to save uploaded files, injected from application properties
    @Value("${app.storage.path}")
    private String SAVE_LOCATION;

    /// Servlet context for determining MIME types
    private final ServletContext servletContext;

    /// Constructor for dependency injection
    public VectorServices(VectorsRepo vectorsRepo, ServletContext servletContext) {
        this.vectorsRepo = vectorsRepo;
        this.servletContext = servletContext;
    }

    /// Searches for vectors matching the given search term
    public List<VectorDTO> findBySearchTerm(String term) {
        List<VectorEntity> allVectors = vectorsRepo.findBySearchTerm(term);
        return allVectors.stream()
                .map(vector -> {
                    /// Construct the full URL for the preview image
                    String previewImageUrl = "http://localhost:8080" + "/files/" + vector.getPreviewPath();
                    return new VectorDTO(vector.getVectorId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }

    /// Handles uploading of vector files and their associated preview images to the database
    public VectorEntity uploadFiles(String title, String description, String creator, String keywords, MultipartFile previewImage, MultipartFile file) throws IOException {
        /// Generate unique filenames to avoid collisions
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String uniquePreviewName = UUID.randomUUID().toString() + "_" + previewImage.getOriginalFilename();

        /// Define subdirectories for vectors and preview images
        String vectorFileSubDir = "vectors_files";
        String previewImageSubDir = "preview_images";

        /// construct the directory paths in which the files will be saved
        Path vectorUploadDir = Paths.get(SAVE_LOCATION, vectorFileSubDir);
        Path previewUploadDir = Paths.get(SAVE_LOCATION, previewImageSubDir);

        /// construct the full paths for saving the files
        Path targetFilePath = vectorUploadDir.resolve(uniqueFileName);
        Path targetPreviewPath = previewUploadDir.resolve(uniquePreviewName);

        /// copy the vector file from the request to the target directory
        Files.copy(file.getInputStream(), targetFilePath);
        Files.copy(previewImage.getInputStream(), targetPreviewPath);

        /// construct the relative file paths to be saved in the database
        String relativeFilePath = Paths.get(vectorFileSubDir, uniqueFileName).toString();
        String relativePreviewPath = Paths.get(previewImageSubDir, uniquePreviewName).toString();

        /// finally, create the new vector entity and save it to the database
        VectorEntity newVector = new VectorEntity(title, description, creator, keywords, relativePreviewPath, relativeFilePath);
        return vectorsRepo.save(newVector);

    }

    /// Handles downloading of vector files
    public ResponseEntity<Resource> downloadFile(Integer id) throws MalformedURLException {
        /// Get the relative file path from the database and replace backslashes with forward slashes for compatibility with Docker
        String relativePath = vectorsRepo.findByVectorId(id).getFilePath().replace('\\', '/');
        
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
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// Retrieves all vector asset previews
    public List<VectorDTO> getAllPreviews() {
        List<VectorEntity> allVectors = vectorsRepo.findAll();
        return allVectors.stream()
                .map(vector -> {
                    /// Construct the full URL for the preview image
                    String previewImageUrl = "http://localhost:8080" + "/files/" + vector.getPreviewPath();
                    return new VectorDTO(vector.getVectorId(), previewImageUrl);
                })
                .collect(Collectors.toList());
    }
}
