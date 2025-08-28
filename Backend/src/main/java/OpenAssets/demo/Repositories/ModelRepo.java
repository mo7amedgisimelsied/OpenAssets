package OpenAssets.demo.Repositories;

import OpenAssets.demo.Entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/// Repisitory interface for ModelEntity, provides methods to interact with the database.
@Repository
public interface ModelRepo extends JpaRepository<ModelEntity, Integer> {

    /// Custom query to search models by keywords using full-text search.
    @Query(value = "SELECT * FROM models " +
            "WHERE MATCH(keywords) AGAINST (:term IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    List<ModelEntity> findBySearchTerm(String term);
    /// Find a model by its unique ID, used to fetch file path for download
    ModelEntity findByModelId(Integer id);

}
