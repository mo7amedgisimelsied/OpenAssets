package OpenAssets.demo.Services;

import OpenAssets.demo.Entities.Icons;
import OpenAssets.demo.Repositories.IconsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconsServices {

    private final IconsRepo iconsRepo;

    public IconsServices(IconsRepo iconsRepo) {
        this.iconsRepo = iconsRepo;
    }

    public List<Icons> findAllIcons(){
        return iconsRepo.findAll();
    }

    public List<Icons> findBySearchTerm(String term){
        return iconsRepo.findBySearchTerm(term);
    }

    public Icons uploadFiles(String title, String description, String creator, String keywords, String svgCode) {
        Icons newIcon = new Icons(title, description, creator, keywords, svgCode);
        return iconsRepo.save(newIcon);
    }
}
