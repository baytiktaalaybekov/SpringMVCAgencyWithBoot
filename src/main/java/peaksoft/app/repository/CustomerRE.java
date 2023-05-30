package peaksoft.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.app.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRE extends JpaRepository<Customer,Long> {

    @Query("from Customer c join c.agencies a where a.id= :id")
    List<Customer> getAllCustomers(Long id);


}
