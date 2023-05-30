package peaksoft.app.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.app.entity.Agency;
import peaksoft.app.entity.House;
import peaksoft.app.repository.AgencyRe;
import peaksoft.app.service.AgencySe;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AgencySeImpl implements AgencySe {

    private final AgencyRe agencyRe;


    @Override
    public void saveAgency(Agency agency) {
        agencyRe.save(agency);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyRe.findAll();
    }

    @Override
    public Agency getAgencyById(Long id) {
        Agency agency = agencyRe.findById(id).get();
        //ADD NOT FOUND
        return agency;
    }

    @Override
    public void update(Long id, Agency agency) {
        Agency agency1 = agencyRe.findById(id).
                orElseThrow(()-> new RuntimeException("Agency with id: " +id+ "not found"));
        agency1.setName(agency.getName());
        //TODO UPDATE NOT SAME VALUES
        agency1.setCountry(agency.getCountry());
        agency1.setPhoneNumber(agency.getPhoneNumber());
        agency1.setEmail(agency.getEmail());
        agency1.setImage_Link(agency.getImage_Link());
        agencyRe.save(agency1);
    }

    @Override
    public String deleteAgencyById(Long id) {
        boolean b = agencyRe.existsById(id);
        if (!b) {
            throw new NoSuchElementException("Agency with id: " +id+ "not found");
        }
        agencyRe.deleteById(id);
        return "Agency with id: " + id+ "is deleted";

    }


}
