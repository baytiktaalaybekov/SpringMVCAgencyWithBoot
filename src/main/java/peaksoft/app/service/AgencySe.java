package peaksoft.service;

import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRe;

import java.util.List;
import java.util.Optional;

public interface AgencySe {
    void saveAgency(Agency agency);
    List<Agency> getAllAgency();
    Agency getAgencyById(Long id);
    void updateAgency(Long id, Agency agency);
    void deleteAgencyById(Long id);
    List<Agency> search (String word);
    List<House> getAllHouseToAgency(Long agencyId);

}
