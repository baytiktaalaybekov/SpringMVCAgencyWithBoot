package peaksoft.app.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.app.entity.Agency;
import peaksoft.app.entity.Customer;
import peaksoft.app.repository.AgencyRe;
import peaksoft.app.repository.CustomerRE;
import peaksoft.app.service.CustomerSe;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerSeImpl implements CustomerSe {

    private final CustomerRE customerRE;
    private final AgencyRe agencyRe;

    @Override
    public void saveCustomer(Customer customer, Long agencyId) {
        Agency agency = agencyRe.findById(agencyId).orElseThrow(() -> new NullPointerException("Agency with id: " + agencyId + "Not found"));
        agency.setCustomers(List.of(customer));
        customer.setAgencies(List.of(agency));
        customerRE.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRE.findAll();
    }

    @Override
    public List<Customer> getAllCustomers(Long id) {
        return customerRE.getAllCustomers(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerRE.findById(id).orElseThrow(() -> new RuntimeException("Customer with id: " + id + "not found"));
        return customer;
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer customer1 = customerRE.findById(id).orElseThrow(() -> new RuntimeException("Customer with id: " + id + "not found"));
        customer1.setName(customer.getName());
        customer1.setSurname(customer.getSurname());
        customer1.setEmail(customer.getEmail());
        customer1.setGender(customer.getGender());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setDate_of_birth(customer.getDate_of_birth());
        customerRE.save(customer1);
    }

    @Override
    public String deleteCustomerById(Long id) {
        boolean b = customerRE.existsById(id);
        if (!b){
            throw new NullPointerException("Customer with id: " + id + "not found");
        }
        customerRE.deleteById(id);
        return "Customer with id: "+id+" is delete";
    }


}
