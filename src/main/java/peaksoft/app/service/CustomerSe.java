package peaksoft.app.service;



import peaksoft.app.entity.Customer;

import java.util.List;

public interface CustomerSe {
    void saveCustomer(Customer customer, Long agencyId);
    List<Customer> getAllCustomer();
    List<Customer> getAllCustomers(Long id);
    Customer getCustomerById(Long id);
    void updateCustomer(Long id, Customer customer);
    String deleteCustomerById(Long id);
//    void assignCustomerToAgency(Long customerId,List<Long> agencyId);
}
