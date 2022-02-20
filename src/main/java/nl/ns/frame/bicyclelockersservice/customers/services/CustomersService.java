package nl.ns.frame.bicyclelockersservice.customers.services;

import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.models.Customer;
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

    public Customer retrieveCustomer(final String id) {
        return customersRepository.findById(id);
    }

    public List<Customer> retrieveAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer createCustomer(final CustomersRequest customersRequest) {
        final Customer customer = new Customer(null, customersRequest.getName());

        return customersRepository.save(customer);
    }
}
