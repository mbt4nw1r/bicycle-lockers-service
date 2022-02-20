package nl.ns.frame.bicyclelockersservice.bicyclelockers.controllers;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.models.BicycleLockersRequest;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLockers;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.services.BicycleLockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bicycle-lockers")
public class BicycleLockersController {

    private final BicycleLockersService bicycleLockersService;

    @Autowired
    public BicycleLockersController(final BicycleLockersService bicycleLockersService) {
        this.bicycleLockersService = bicycleLockersService;
    }

    @PostMapping
    public ResponseEntity<BicycleLockers> createBicycleLockers(@RequestBody final BicycleLockersRequest bicycleLockersRequest) {
        bicycleLockersService.createBicycleLocker(bicycleLockersRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<BicycleLockers> updateBicycleLockers(@PathVariable final String id, @RequestBody final BicycleLockersRequest bicycleLockersRequest) {
        bicycleLockersService.updateBicycleLocker(id, bicycleLockersRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BicycleLockers> deleteBicycleLockers(@PathVariable final String id) {
        return bicycleLockersService.deleteBicycleLocker(id);
    }
}
