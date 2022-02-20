package nl.ns.frame.bicyclelockersservice.customers.repositories;

import nl.ns.frame.bicyclelockersservice.customers.repositories.entities.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomersRepositoryTest {

    @Autowired
    private CustomersRepository customersRepository;

    @Test
    void testFindById() {
        assertThat(customersRepository).isNotNull();
        final Customers customers = customersRepository.findById("dd01b984-e7f1-466a-91a1-6f2b17042a1e");
        assertThat(customers).isNotNull();
        assertThat(customers.getId()).isEqualTo("dd01b984-e7f1-466a-91a1-6f2b17042a1e");
        assertThat(customers.getName()).isEqualTo("Piet Robinson");
    }

    @Test
    void testFindAll() {
        assertThat(customersRepository).isNotNull();
        final List<Customers> customersList = customersRepository.findAll();
        assertThat(customersList).isNotEmpty();
        final Customers customers = customersList.get(0);
        assertThat(customers).isNotNull();
        assertThat(customers.getId()).isEqualTo("dd01b984-e7f1-466a-91a1-6f2b17042a1e");
        assertThat(customers.getName()).isEqualTo("Piet Robinson");
    }

}
