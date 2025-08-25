package OpenAssets.demo.Controllers;

import OpenAssets.demo.Entities.Icons;
import OpenAssets.demo.Services.IconsServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("icons")
public class IconsController {


    private final IconsServices iconsServices;

    public IconsController(IconsServices iconsServices) {
        this.iconsServices = iconsServices;
    }

    @GetMapping()
    public List<Icons> findAllIcons(){
    return iconsServices.findAllIcons();
}

    @GetMapping("/search/term")
    public List<Icons> findBySearchTerm(@RequestParam String term){
        return iconsServices.findBySearchTerm(term);
    }

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
