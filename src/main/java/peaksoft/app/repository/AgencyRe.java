package peaksoft.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.app.entity.Agency;

import java.util.List;
@Repository
public interface AgencyRe extends JpaRepository<Agency,Long> {

    @Query("delete from Agency where id=:id")
    String deleteByAgencyId(Long id);

}
