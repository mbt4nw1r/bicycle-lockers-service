package nl.ns.frame.bicyclelockersservice.customers.services;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.services.BicycleLockersService;
import nl.ns.frame.bicyclelockersservice.customers.models.CustomersRequest;
import nl.ns.frame.bicyclelockersservice.customers.repositories.CustomersRepository;
import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customer;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.exceptions.BicycleLockersBadRequestException;
import nl.ns.frame.bicyclelockersservice.customers.exceptions.CustomersBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    private static final String AVAILABLE = "AVAILABLE";
    private static final String RESERVED = "RESERVED";
    private final CustomersRepository customersRepository;
    private final BicycleLockersService bicycleLockersService;

    @Autowired
    public CustomersService(final CustomersRepository customersRepository, final BicycleLockersService bicycleLockersService) {
        this.customersRepository = customersRepository;
        this.bicycleLockersService = bicycleLockersService;
    }

    public Customer retrieveCustomer(final String id) {
        return customersRepository.findById(id);
    }

    public List<Customer> retrieveAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer createCustomer(final CustomersRequest customersRequest) {
        final Customer customer = new Customer(null, null, customersRequest.getName());

        return customersRepository.save(customer);
    }

    public Customer reserveBicycleLockers(final String customerId, final String bicycleLockerId) {
        final Customer customer = retrieveCustomer(customerId);
        if (customer == null) {
            throw new CustomersBadRequestException("Customer cannot be found");
        }
        final BicycleLocker bicycleLocker = bicycleLockersService.findBicycleLocker(bicycleLockerId);
        if (bicycleLocker == null) {
            throw new BicycleLockersBadRequestException("Bicycle locker cannot be found");
        }
        if (!AVAILABLE.equals(bicycleLocker.getStatus())) {
            throw new BicycleLockersBadRequestException("Bicycle locker is not available");
        }
        final List<BicycleLocker> bicycleLockerList = createBicycleLockerListWithReservedStatus(customer, bicycleLocker);
        customer.setBicycleLockerList(bicycleLockerList);
        return customersRepository.save(customer);
    }

    private List<BicycleLocker> createBicycleLockerListWithReservedStatus(Customer customer, BicycleLocker bicycleLocker) {
        final List<BicycleLocker> bicycleLockerList = customer.getBicycleLockerList();
        bicycleLocker.setStatus(RESERVED);
        bicycleLockerList.add(bicycleLocker);
        return bicycleLockerList;
    }
}
