package nl.ns.frame.bicyclelockersservice.bicyclelockers.services;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.models.BicycleLockersRequest;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.BicycleLockersRepository;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BicycleLockerServiceTest {

    @Mock
    private BicycleLockersRepository bicycleLockersRepository;

    @InjectMocks
    private BicycleLockersService bicycleLockersService;

    @Test
    void testCreateBicycleLocker() {
        final BicycleLocker bicycleLocker = new BicycleLocker("a7fdb2fd-f743-477d-a67b-93c00792ead6", "A3", "AVAILABLE");
        when(bicycleLockersRepository.save(any(BicycleLocker.class))).thenReturn(bicycleLocker);
        final BicycleLocker result = bicycleLockersService.createBicycleLocker(BicycleLockersRequest.builder().readableId("A3").status("AVAILABLE").build());
        assertThat(result).isNotNull();
        assertThat(result.getReadableId()).isEqualTo("A3");
        assertThat(result.getStatus()).isEqualTo("AVAILABLE");
    }

    @Test
    void testUpdateBicycleLocker() {
        final BicycleLocker bicycleLocker = new BicycleLocker("a7fdb2fd-f743-477d-a67b-93c00792ead6", "A3", "AVAILABLE");
        final BicycleLocker updatedBicycleLocker = new BicycleLocker("a7fdb2fd-f743-477d-a67b-93c00792ead6", "A4", "AVAILABLE");
        when(bicycleLockersRepository.findById(anyString())).thenReturn(bicycleLocker);
        when(bicycleLockersRepository.save(any(BicycleLocker.class))).thenReturn(updatedBicycleLocker);
        final BicycleLocker result = bicycleLockersService.updateBicycleLocker("a7fdb2fd-f743-477d-a67b-93c00792ead6", BicycleLockersRequest.builder().readableId("A4").status("AVAILABLE").build());
        assertThat(result).isNotNull();
        assertThat(result.getReadableId()).isEqualTo("A4");
        assertThat(result.getStatus()).isEqualTo("AVAILABLE");
    }

    @Test
    void givenExistingIdExpectedNoContentResponse() {
        when(bicycleLockersRepository.deleteById(anyString())).thenReturn(1);
        final ResponseEntity<BicycleLocker> result = bicycleLockersService.deleteBicycleLocker("a7fdb2fd-f743-477d-a67b-93c00792ead6");
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void givenNotExistingIdExpectedBadRequestResponse() {
        when(bicycleLockersRepository.deleteById(anyString())).thenReturn(0);
        final ResponseEntity<BicycleLocker> result = bicycleLockersService.deleteBicycleLocker("NotExistingId");
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
