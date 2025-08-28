package OpenAssets.demo.Repositories;

import OpenAssets.demo.Entities.Icons;
import OpenAssets.demo.Entities.VectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/// Repisitory interface for Vectors entity, provides methods to interact with the database.
@Repository
public interface VectorsRepo extends JpaRepository<VectorEntity, Integer> {

    /// Custom query to search vectors by keywords using full-text search.
    @Query(value = "SELECT * FROM vectors " +
            "WHERE MATCH(keywords) AGAINST (:term IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    List<VectorEntity> findBySearchTerm(String term);
    /// Find a vector by its unique ID, used to fetch file path for download
    VectorEntity findByVectorId(Integer id);

}
