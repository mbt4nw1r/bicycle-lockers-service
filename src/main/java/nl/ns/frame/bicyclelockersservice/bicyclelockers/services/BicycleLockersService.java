package nl.ns.frame.bicyclelockersservice.bicyclelockers.services;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.models.BicycleLockersRequest;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.BicycleLockersRepository;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLockers;
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

    public BicycleLockers createBicycleLocker(final BicycleLockersRequest bicycleLockersRequest) {
        final BicycleLockers bicycleLockers = new BicycleLockers(null, bicycleLockersRequest.getReadableId(), bicycleLockersRequest.getStatus());
        return bicycleLockersRepository.save(bicycleLockers);
    }

    public BicycleLockers updateBicycleLocker(final String id, final BicycleLockersRequest bicycleLockersRequest) {
        final BicycleLockers bicycleLockers = bicycleLockersRepository.findById(id);
        bicycleLockers.setReadableId(bicycleLockersRequest.getReadableId());
        bicycleLockers.setStatus(bicycleLockersRequest.getStatus());
        return bicycleLockersRepository.save(bicycleLockers);
    }

    public ResponseEntity<BicycleLockers> deleteBicycleLocker(final String id) {
        final Integer result = bicycleLockersRepository.deleteById(id);
        return result != 1 ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }
}
