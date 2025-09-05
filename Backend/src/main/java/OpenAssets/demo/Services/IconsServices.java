package OpenAssets.demo.Services;

import OpenAssets.demo.Entities.IconEntity;
import OpenAssets.demo.Repositories.IconsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/// Service class for managing icon-related logic
@Service
public class IconsServices {

    /// Repository for accessing icon data
    private final IconsRepo iconsRepo;
    /// Constructor for dependency injection of the IconsRepo
    public IconsServices(IconsRepo iconsRepo) {
        this.iconsRepo = iconsRepo;
    }

    /// Retrieves all icons from the repository
    public List<IconEntity> findAllIcons(){
        return iconsRepo.findAll();
    }

    /// Searches for icons based on a search term
    public List<IconEntity> findBySearchTerm(String term){
        return iconsRepo.findBySearchTerm(term);
    }

    /// Uploads a new icon to the database with the provided details and SVG code
    public IconEntity uploadFiles(String title, String description, String creator, String keywords, String svgCode) {
        IconEntity newIcon = new IconEntity(title, description, creator, keywords, svgCode);
        return iconsRepo.save(newIcon);
    }
}