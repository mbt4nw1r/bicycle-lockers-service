package nl.ns.frame.bicyclelockersservice.bicyclelockers.services;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.models.BicycleLockersRequest;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.BicycleLockersRepository;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BicycleLockersService {

    private final BicycleLockersRepository bicycleLockersRepository;

    @Autowired
    public BicycleLockersService(final BicycleLockersRepository bicycleLockersRepository) {
        this.bicycleLockersRepository = bicycleLockersRepository;
    }

    public BicycleLocker createBicycleLocker(final BicycleLockersRequest bicycleLockersRequest) {
        final BicycleLocker bicycleLocker = new BicycleLocker(null, bicycleLockersRequest.getReadableId(), bicycleLockersRequest.getStatus());
        return bicycleLockersRepository.save(bicycleLocker);
    }

    public BicycleLocker updateBicycleLocker(final String id, final BicycleLockersRequest bicycleLockersRequest) {
        final BicycleLocker bicycleLocker = bicycleLockersRepository.findById(id);
        bicycleLocker.setReadableId(bicycleLockersRequest.getReadableId());
        bicycleLocker.setStatus(bicycleLockersRequest.getStatus());
        return bicycleLockersRepository.save(bicycleLocker);
    }

    public ResponseEntity<BicycleLocker> deleteBicycleLocker(final String id) {
        final Integer result = bicycleLockersRepository.deleteById(id);
        return result != 1 ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }

    public BicycleLocker findBicycleLocker(final String id) {
        return bicycleLockersRepository.findById(id);
    }
}
