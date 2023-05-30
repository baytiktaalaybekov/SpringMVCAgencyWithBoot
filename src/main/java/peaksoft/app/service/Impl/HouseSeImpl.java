package peaksoft.app.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.app.entity.Agency;
import peaksoft.app.entity.House;
import peaksoft.app.repository.AgencyRe;
import peaksoft.app.repository.HouseRe;
import peaksoft.app.service.HouseSe;


import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseSeImpl implements HouseSe {

    private final HouseRe houseRe;
    private final AgencyRe agencyRe;

    @Override
    public void saveHouse(Long agencyId, House house) {
        Agency agency = agencyRe.findById(agencyId).get();
        house.setAgencies(agency);
        houseRe.save(house);

    }

    @Override
    public List<House> getAllHouse(Long agencyId) {
        return houseRe.getAllHouse(agencyId);
    }

    @Override
    public List<House> getAllHouses() {
        return houseRe.findAll();
    }

    @Override
    public String deleteHouseById(Long id) {
        boolean b = houseRe.existsById(id);
        if (!b) {
            throw new RuntimeException("House with id: " +id+ " not found !");
        }
        houseRe.deleteById(id);
        return "House with id: " +id+ "is deleted!";
    }

    @Override
    public void updateHouse(Long id, House house) {
        House house1 = houseRe.findById(id).orElseThrow(()-> new RuntimeException("House with id:"+id+"not found"));
        house1.setHouseType(house.getHouseType());
        house1.setAddress(house.getAddress());
        house1.setPrice(house.getPrice());
        house1.setRoom(house.getRoom());
        house1.setCountry(house.getCountry());
        house1.setDescription(house.getDescription());
        house1.setIs_Booked(house.getIs_Booked());
        house1.setImage_Link(house.getImage_Link());
        houseRe.save(house1);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRe.findById(id).orElseThrow(() -> new RuntimeException("House with id: " + id + "not found"));

    }
}
