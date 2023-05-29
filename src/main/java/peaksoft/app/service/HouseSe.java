package peaksoft.service;

import peaksoft.entity.House;

import java.util.List;

public interface HouseSe {
    void saveHouse (Long agencyId, House house);

    List<House> getAllHouse(Long agencyId);

    List<House> getAllHouses();

    void deleteHouse(Long id);

    void updateHouse(Long id, House house);

    House getHouseById(Long id);
}
