package nl.ns.frame.bicyclelockersservice.customers.services;

import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customers;
import nl.ns.frame.bicyclelockersservice.customers.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(final CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customers retrieveCustomer(final String id) {
        return customersRepository.findById(id);
    }

    public List<Customers> retrieveAllCustomers() {
        return customersRepository.findAll();
    }

    public Customers createCustomer(final CustomersRequest customersRequest) {
        final Customers customers = new Customers(null, customersRequest.getName());

        return customersRepository.save(customers);
    }
}
