package nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleLockersRepository extends CrudRepository<BicycleLocker, Long> {
    BicycleLocker findById(String id);

    Integer deleteById(String id);
}
