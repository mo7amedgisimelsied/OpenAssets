package OpenAssets.demo.Controllers;

import OpenAssets.demo.Entities.Icons;
import OpenAssets.demo.Services.IconsServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
