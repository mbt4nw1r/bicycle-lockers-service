package nl.ns.frame.bicyclelockersservice.bicyclelockers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.models.BicycleLockersRequest;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.services.BicycleLockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bicycle-lockers")
public class BicycleLockersController {

    private final BicycleLockersService bicycleLockersService;

    @Autowired
    public BicycleLockersController(final BicycleLockersService bicycleLockersService) {
        this.bicycleLockersService = bicycleLockersService;
    }

    @PostMapping
    @Operation(summary = "Create a bicycle locker")
    public ResponseEntity<BicycleLocker> createBicycleLocker(@RequestBody final BicycleLockersRequest bicycleLockersRequest) {
        bicycleLockersService.createBicycleLocker(bicycleLockersRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a bicycle locker")
    public ResponseEntity<BicycleLocker> updateBicycleLocker(@PathVariable final String id, @RequestBody final BicycleLockersRequest bicycleLockersRequest) {
        bicycleLockersService.updateBicycleLocker(id, bicycleLockersRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a bicycle locker")
    public ResponseEntity<BicycleLocker> deleteBicycleLocker(@PathVariable final String id) {
        return bicycleLockersService.deleteBicycleLocker(id);
    }
}
