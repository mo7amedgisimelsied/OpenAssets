package OpenAssets.demo.Repositories;

import OpenAssets.demo.Entities.Icons;
import OpenAssets.demo.Entities.VectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VectorsRepo extends JpaRepository<VectorEntity, Integer> {

    @Query(value = "SELECT * FROM vectors " +
            "WHERE MATCH(keywords) AGAINST (:term IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    List<VectorEntity> findBySearchTerm(String term);

    VectorEntity findByVectorId(Integer id);

}
