package nl.ns.frame.bicyclelockersservice.customers.services;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.services.BicycleLockersService;
import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.CustomersRepository;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customer;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.exceptions.BicycleLockersBadRequestException;
import nl.ns.frame.bicyclelockersservice.customers.exceptions.CustomersBadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private BicycleLockersService bicycleLockersService;

    @InjectMocks
    private CustomersService customersService;

    @Test
    void testRetrieveCustomer() {
        final Customer customer = new Customer("1", null, "Test Tester");
        when(customersRepository.findById(anyString())).thenReturn(customer);
        final Customer result = customersService.retrieveCustomer("1");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getName()).isEqualTo("Test Tester");
    }

    @Test
    void testRetrieveAllCustomers() {
        final Customer customer = new Customer("1", null, "Test Tester");
        when(customersRepository.findAll()).thenReturn(List.of(customer));
        final List<Customer> customerList = customersService.retrieveAllCustomers();
        assertThat(customerList).isNotEmpty();
        assertThat(customerList.get(0).getId()).isEqualTo("1");
        assertThat(customerList.get(0).getName()).isEqualTo("Test Tester");
    }

    @Test
    void testCreateCustomer() {
        final Customer customer = new Customer("1", null, "Test Tester");
        when(customersRepository.save(any(Customer.class))).thenReturn(customer);
        final Customer result = customersService.createCustomer(CustomersRequest.builder().name("Test Tester").build());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Tester");
    }

    @Test
    void givenValidParametersCallingReserveBicycleLockersExpectedCustomer() {
        final BicycleLocker bicycleLocker = new BicycleLocker("2", "A2", "AVAILABLE");
        final BicycleLocker reservedBicycleLocker = new BicycleLocker("2", "A2", "RESERVED");
        final Customer customer = new Customer("1", new ArrayList<>(), "Test Tester");
        final Customer updatedCustomer = new Customer("1", List.of(reservedBicycleLocker), "Test Tester");
        when(customersRepository.findById(anyString())).thenReturn(customer);
        when(bicycleLockersService.findBicycleLocker(anyString())).thenReturn(bicycleLocker);
        when(customersRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
        final Customer result = customersService.reserveBicycleLockers("1", "2");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getName()).isEqualTo("Test Tester");
        assertThat(result.getBicycleLockerList()).isNotEmpty();
        assertThat(result.getBicycleLockerList().get(0).getId()).isEqualTo("2");
        assertThat(result.getBicycleLockerList().get(0).getReadableId()).isEqualTo("A2");
        assertThat(result.getBicycleLockerList().get(0).getStatus()).isEqualTo("RESERVED");
    }

    @Test
    void givenInvalidCustomerIdExpectedException() {
        when(customersRepository.findById(anyString())).thenReturn(null);
        assertThrows(CustomersBadRequestException.class, () -> customersService.reserveBicycleLockers("1", "2"));
    }

    @Test
    void givenInvalidBicycleLockerIdExpectedException() {
        final Customer customer = new Customer("1", new ArrayList<>(), "Test Tester");
        when(customersRepository.findById(anyString())).thenReturn(customer);
        when(bicycleLockersService.findBicycleLocker(anyString())).thenReturn(null);
        assertThrows(BicycleLockersBadRequestException.class, () -> customersService.reserveBicycleLockers("1", "2"));
    }

    @Test
    void givenBicycleLockerWithRentedStatusExpectedException() {
        final BicycleLocker bicycleLocker = new BicycleLocker("2", "A2", "RENTED");
        final Customer customer = new Customer("1", new ArrayList<>(), "Test Tester");
        when(customersRepository.findById(anyString())).thenReturn(customer);
        when(bicycleLockersService.findBicycleLocker(anyString())).thenReturn(bicycleLocker);
        assertThrows(BicycleLockersBadRequestException.class, () -> customersService.reserveBicycleLockers("1", "2"));
    }
}
