package peaksoft.app.service;


import peaksoft.app.entity.Agency;
import peaksoft.app.entity.House;

import java.util.List;

public interface AgencySe {
    void saveAgency(Agency agency);
    List<Agency> getAllAgency();
    Agency getAgencyById(Long id);
    void update(Long id, Agency agency);
    String deleteAgencyById(Long id);
//    List<Agency> search (String word);
//    List<House> getAllHouseToAgency(Long agencyId);

}
