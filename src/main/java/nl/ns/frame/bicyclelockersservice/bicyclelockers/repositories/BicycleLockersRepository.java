package nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLockers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleLockersRepository extends CrudRepository<BicycleLockers, Long> {
    BicycleLockers findById(String id);

    Integer deleteById(String id);
}
