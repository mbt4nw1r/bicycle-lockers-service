package nl.ns.frame.bicyclelockersservice.customers.controllers;

import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customer;
import nl.ns.frame.bicyclelockersservice.customers.services.CustomersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(final CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> retrieveAllCustomer() {
        final List<Customer> customerList = customersService.retrieveAllCustomers();

        return ResponseEntity.ok(customerList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> retrieveCustomer(@PathVariable final String id) {
        final Customer customer = customersService.retrieveCustomer(id);

        return ResponseEntity.ok(customer);
    }

    @PutMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody final CustomersRequest customersRequest) {
        customersService.createCustomer(customersRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{customerId}/bicycle-lockers/{bicycleLockerId}")
    public ResponseEntity<Customer> reserveBicycleLocker(@PathVariable final String customerId, @PathVariable final String bicycleLockerId) {
        customersService.reserveBicycleLockers(customerId, bicycleLockerId);

        return ResponseEntity.noContent().build();
    }

}
