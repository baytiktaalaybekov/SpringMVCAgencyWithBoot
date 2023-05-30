package peaksoft.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.app.entity.House;

import java.util.List;

@Repository
public interface HouseRe extends JpaRepository<House,Long> {

    @Query("from House h join Agency a where  h.agencies.id = :agencyId")
    List<House> getAllHouse(Long agencyId);


    void deleteHouseById(Long id);
}
