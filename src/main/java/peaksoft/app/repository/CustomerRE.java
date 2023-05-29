package peaksoft.repository;

import peaksoft.entity.Customer;

import java.util.List;

public interface CustomerRE {
    void saveCustomer(Customer customer, Long agencyId);
    List<Customer> getAllCustomer();
    List<Customer> getAllCustomers(Long id);

    Customer getCustomerById(Long id);
    void updateCustomer(Long id, Customer customer);
    void deleteCustomerById(Long id);
    void assignCustomerToAgency(Long customerId,List<Long> agencyId);
}