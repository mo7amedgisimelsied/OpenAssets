package OpenAssets.demo.Controllers;

import OpenAssets.demo.DTOs.ModelDTO;
import OpenAssets.demo.Entities.ModelEntity;
import OpenAssets.demo.Services.ModelServices;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/// Controller to handle HTTP requests related to 3D assets
@RestController
@RequestMapping("models")
public class ModelController {

    /// Dependency on ModelServices to handle business logic
    private final ModelServices modelServices;
    private static final Logger log = Logger.getLogger(ModelController.class.getName());

    /// Constructor injection of ModelServices
    public ModelController(ModelServices modelServices) {
        this.modelServices = modelServices;
    }

    /// Endpoint to retrieve all 3D asset previews
    @GetMapping()
    public List<ModelDTO> getAllPreviews(){
        return modelServices.getAllPreviews();
    }

    /// Endpoint to search 3D assets by a search term
    @GetMapping("/search/term")
    public List<ModelDTO> findBySearchTerm(@RequestParam String term){
        return modelServices.findBySearchTerm(term);
    }

    /// Endpoint to upload a new 3D asset to the database
    @PostMapping("upload")
    public ResponseEntity<ModelEntity> uploadFiles(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("creator") String creator,
            @RequestParam("keywords") String keywords,
            @RequestParam("previewImage") MultipartFile previewImage,
            @RequestParam("file") MultipartFile file
    ){
        try {
            ModelEntity savedModel = modelServices.uploadFiles(title, description, creator, keywords, previewImage, file);
            return new ResponseEntity<>(savedModel, HttpStatus.CREATED);
        }
        catch (IOException e){
            log.log(Level.SEVERE, "Exception during upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /// Endpoint to download a 3D asset file by its ID
    @GetMapping("download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer id) throws MalformedURLException {
        return modelServices.downloadFile(id);
    }

}
