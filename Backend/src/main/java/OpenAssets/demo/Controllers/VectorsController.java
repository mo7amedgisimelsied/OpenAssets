package OpenAssets.demo.Controllers;


import OpenAssets.demo.DTOs.VectorDTO;
import OpenAssets.demo.Entities.VectorEntity;
import OpenAssets.demo.Services.VectorServices;
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

/// Controller to handle HTTP requests related to vector assets
@RestController
@RequestMapping("vectors")
public class VectorsController {

    /// Dependency on VectorServices to handle business logic
    private final VectorServices vectorServices;
    private static final Logger log = Logger.getLogger(VectorsController.class.getName());

    /// Constructor injection of VectorServices
    public VectorsController(VectorServices vectorServices) {
        this.vectorServices = vectorServices;
    }

    /// Endpoint to retrieve all vector asset previews
    @GetMapping()
    public List<VectorDTO> getAllPreviews(){
        return vectorServices.getAllPreviews();
    }

    /// Endpoint to search vector assets by a search term
    @GetMapping("/search/term")
    public List<VectorDTO> findBySearchTerm(@RequestParam String term){
        return vectorServices.findBySearchTerm(term);
    }

    /// Endpoint to upload a new vector asset to the database
    @PostMapping("upload")
    public ResponseEntity<VectorEntity> uploadFiles(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("creator") String creator,
            @RequestParam("keywords") String keywords,
            @RequestParam("previewImage") MultipartFile previewImage,
            @RequestParam("file") MultipartFile file

    ){
        try {
            VectorEntity savedVector = vectorServices.uploadFiles(title, description, creator, keywords, previewImage, file);
            return new ResponseEntity<>(savedVector, HttpStatus.CREATED);

        }
        catch (IOException e){
            log.log(Level.SEVERE, "Exception during upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /// Endpoint to download a vector asset file by its ID
    @GetMapping("download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer id) throws MalformedURLException {
        return vectorServices.downloadFile(id);
    }
}
