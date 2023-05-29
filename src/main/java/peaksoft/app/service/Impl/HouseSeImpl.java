package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.House;
import peaksoft.repository.HouseRe;
import peaksoft.service.HouseSe;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HouseSeImpl implements HouseSe {

    private final HouseRe houseRe;

    @Override
    public void saveHouse(Long agencyId, House house) {
        houseRe.saveHouse(agencyId,house);
    }

    @Override
    public List<House> getAllHouse(Long agencyId) {
        return houseRe.getAllHouse(agencyId);
    }

    @Override
    public List<House> getAllHouses() {
        return houseRe.getAllHouses();
    }



    @Override
    public void deleteHouse(Long id) {
        houseRe.deleteHouse(id);
    }

    @Override
    public void updateHouse(Long id, House house) {
         houseRe.updateHouse(id,house);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRe.getHouseById(id);
    }
}
