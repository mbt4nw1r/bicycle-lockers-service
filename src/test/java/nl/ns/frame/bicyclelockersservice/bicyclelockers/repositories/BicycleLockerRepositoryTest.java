package nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BicycleLockerRepositoryTest {

    @Autowired
    private BicycleLockersRepository bicycleLockersRepository;

    @Test
    void testDeleteById() {
        assertThat(bicycleLockersRepository).isNotNull();
        final Integer result = bicycleLockersRepository.deleteById("f873f340-a419-45eb-819e-4b6da1efd7ff");
        assertThat(result).isEqualTo(1);
    }

}
