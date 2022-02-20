package nl.ns.frame.bicyclelockersservice.bicyclelockers.controllers;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.models.BicycleLockersRequest;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLockers;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.services.BicycleLockersService;
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
class BicycleLockersControllerTest {

    @Mock
    private BicycleLockersService bicycleLockersService;

    @InjectMocks
    private BicycleLockersController bicycleLockersController;

    @Test
    void testCreateBicycleLockers() {
        final BicycleLockers bicycleLockers = new BicycleLockers("a7fdb2fd-f743-477d-a67b-93c00792ead6", "A3", "AVAILABLE");
        final BicycleLockersRequest bicycleLockersRequest = BicycleLockersRequest.builder().readableId("A3").status("AVAILABLE").build();
        when(bicycleLockersService.createBicycleLocker(any(BicycleLockersRequest.class))).thenReturn(bicycleLockers);
        final ResponseEntity<BicycleLockers> result = bicycleLockersController.createBicycleLockers(bicycleLockersRequest);
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
    }

    @Test
    void testUpdateBicycleLockers() {
        final BicycleLockers bicycleLockers = new BicycleLockers("a7fdb2fd-f743-477d-a67b-93c00792ead6", "A3", "AVAILABLE");
        final BicycleLockersRequest bicycleLockersRequest = BicycleLockersRequest.builder().readableId("A4").status("AVAILABLE").build();
        when(bicycleLockersService.updateBicycleLocker(anyString(), any(BicycleLockersRequest.class))).thenReturn(bicycleLockers);
        final ResponseEntity<BicycleLockers> result = bicycleLockersController.updateBicycleLockers("a7fdb2fd-f743-477d-a67b-93c00792ead6", bicycleLockersRequest);
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
    }

    @Test
    void testDeleteBicycleLockers() {
        when(bicycleLockersService.deleteBicycleLocker(anyString())).thenReturn(ResponseEntity.noContent().build());
        final ResponseEntity<BicycleLockers> result = bicycleLockersController.deleteBicycleLockers("a7fdb2fd-f743-477d-a67b-93c00792ead6");
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
    }
}
