package peaksoft.repository;

import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface HouseRe {

    void saveHouse (Long agencyId, House house);

    List<House> getAllHouses();

    List<House> getAllHouse(Long agencyId);

    void deleteHouse(Long id);

    void updateHouse(Long id, House house);

    House getHouseById(Long id);
}
