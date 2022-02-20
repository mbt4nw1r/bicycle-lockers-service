package nl.ns.frame.bicyclelockersservice.customers.services;

import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.CustomersRepository;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersService customersService;

    @Test
    void testRetrieveCustomer() {
        final Customers customers = new Customers("1", "Test Tester");
        when(customersRepository.findById(anyString())).thenReturn(customers);
        final Customers result = customersService.retrieveCustomer("1");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getName()).isEqualTo("Test Tester");
    }

    @Test
    void testRetrieveAllCustomers() {
        final Customers customers = new Customers("1", "Test Tester");
        when(customersRepository.findAll()).thenReturn(List.of(customers));
        final List<Customers> customersList = customersService.retrieveAllCustomers();
        assertThat(customersList).isNotEmpty();
        assertThat(customersList.get(0).getId()).isEqualTo("1");
        assertThat(customersList.get(0).getName()).isEqualTo("Test Tester");
    }

    @Test
    void testCreateCustomer() {
        final Customers customers = new Customers("1", "Test Tester");
        when(customersRepository.save(any(Customers.class))).thenReturn(customers);
        final Customers result = customersService.createCustomer(CustomersRequest.builder().name("Test Tester").build());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Tester");
    }
}
