package OpenAssets.demo.Repositories;

import OpenAssets.demo.Entities.ModelEntity;
import OpenAssets.demo.Entities.VectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepo extends JpaRepository<ModelEntity, Integer> {

    @Query(value = "SELECT * FROM models " +
            "WHERE MATCH(keywords) AGAINST (:term IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    List<ModelEntity> findBySearchTerm(String term);

    ModelEntity findByModelId(Integer id);

}
