package nl.ns.frame.bicyclelockersservice.customers.repositories;

import nl.ns.frame.bicyclelockersservice.customers.repositories.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Long> {
    Customer findById(String id);

    List<Customer> findAll();
}
