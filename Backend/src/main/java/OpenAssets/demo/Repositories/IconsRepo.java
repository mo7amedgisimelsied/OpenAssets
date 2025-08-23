package OpenAssets.demo.Repositories;

import OpenAssets.demo.Entities.Icons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IconsRepo extends JpaRepository<Icons, Integer> {

    @Query(value = "SELECT * FROM icons " +
            "WHERE MATCH(keywords) AGAINST (:term IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    List<Icons> findBySearchTerm(String term);

}
