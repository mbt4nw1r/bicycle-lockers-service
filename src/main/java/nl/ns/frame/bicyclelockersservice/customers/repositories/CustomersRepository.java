package nl.ns.frame.bicyclelockersservice.customers.repositories;

import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Long> {
    Customers findById(String id);

    List<Customers> findAll();
}
