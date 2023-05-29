package peaksoft.repository;

import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface AgencyRe {
    void saveAgency(Agency agency);
    List<Agency> getAllAgency();
    Agency getAgencyById(Long id);
    void updateAgency(Long id, Agency agency);
    void deleteAgencyById(Long id);
    List<Agency> search (String word);


    List<House> getAllHouseToAgency(Long agencyId);
}
