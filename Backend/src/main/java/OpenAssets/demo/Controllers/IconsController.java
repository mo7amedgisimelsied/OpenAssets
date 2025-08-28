package OpenAssets.demo.Controllers;

import OpenAssets.demo.Entities.Icons;
import OpenAssets.demo.Services.IconsServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// Controller to handle HTTP requests related to Icons
@RestController
@RequestMapping("icons")
public class IconsController {

    /// Dependency on IconsServices to handle business logic
    private final IconsServices iconsServices;
    /// Constructor injection of IconsServices
    public IconsController(IconsServices iconsServices) {
        this.iconsServices = iconsServices;
    }

    /// Endpoint to retrieve all icons from the database
    @GetMapping()
    public List<Icons> findAllIcons(){
    return iconsServices.findAllIcons();
}

    /// Endpoint to search icons by a search term
    @GetMapping("/search/term")
    public List<Icons> findBySearchTerm(@RequestParam String term){
        return iconsServices.findBySearchTerm(term);
    }

    /// Endpoint to upload a new icon to the database
    @PostMapping("upload")
    public Icons uploadFiles(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("creator") String creator,
            @RequestParam("keywords") String keywords,
            @RequestParam("svgCode") String svgCode

    ){
        return iconsServices.uploadFiles(title, description, creator, keywords, svgCode);
    }

}
