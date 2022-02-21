package nl.ns.frame.bicyclelockersservice.customers.controllers;

import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customer;
import nl.ns.frame.bicyclelockersservice.customers.services.CustomersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomersControllerTest {

    @Mock
    private CustomersService customersService;

    @InjectMocks
    private CustomersController customersController;

    @Test
    void testRetrieveAllCustomer() {
        final Customer customer = new Customer("1", null, "Test Tester");
        when(customersService.createCustomer(any(CustomersRequest.class))).thenReturn(customer);
        final ResponseEntity<Customer> result = customersController.createCustomer(CustomersRequest.builder().name("Test Tester").build());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
    }

    @Test
    void testRetrieveCustomer() {
        final Customer customer = new Customer("1", null,"Test Tester");
        when(customersService.retrieveCustomer(anyString())).thenReturn(customer);
        final ResponseEntity<Customer> result = customersController.retrieveCustomer("1");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("1");
        assertThat(result.getBody().getName()).isEqualTo("Test Tester");
    }

    @Test
    void testCreateCustomer() {
        final Customer customer = new Customer("1", null,"Test Tester");
        when(customersService.retrieveAllCustomers()).thenReturn(List.of(customer));
        final ResponseEntity<List<Customer>> result = customersController.retrieveAllCustomers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().get(0)).isNotNull();
        assertThat(result.getBody().get(0).getId()).isEqualTo("1");
        assertThat(result.getBody().get(0).getName()).isEqualTo("Test Tester");
    }
}
