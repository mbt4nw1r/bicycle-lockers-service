package nl.ns.frame.bicyclelockersservice.customers.controllers;

import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customers;
import nl.ns.frame.bicyclelockersservice.customers.services.CustomersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(final CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping
    public ResponseEntity<List<Customers>> retrieveAllCustomer() {
        final List<Customers> customersList = customersService.retrieveAllCustomers();

        return ResponseEntity.ok(customersList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customers> retrieveCustomer(@PathVariable final String id) {
        final Customers customers = customersService.retrieveCustomer(id);

        return ResponseEntity.ok(customers);
    }

    @PutMapping
    public ResponseEntity<Customers> createCustomer(@RequestBody final CustomersRequest customersRequest) {
        customersService.createCustomer(customersRequest);

        return ResponseEntity.noContent().build();
    }

}
