package OpenAssets.demo.Repositories;

import OpenAssets.demo.Entities.IconEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/// Repisitory interface for Icons entity, provides methods to interact with the database.
@Repository
public interface IconsRepo extends JpaRepository<IconEntity, Integer> {

    /// Custom query to search icons by keywords using full-text search.
    @Query(value = "SELECT * FROM icons " +
            "WHERE MATCH(keywords) AGAINST (:term IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)        
    List<IconEntity> findBySearchTerm(String term);

}
