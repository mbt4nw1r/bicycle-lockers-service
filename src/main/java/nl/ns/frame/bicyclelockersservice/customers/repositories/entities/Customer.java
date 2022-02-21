package nl.ns.frame.bicyclelockersservice.customers.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nl.ns.frame.bicyclelockersservice.bicyclelockers.repositories.entities.BicycleLocker;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_bicycle_lockers",
            joinColumns =
                    {@JoinColumn(name = "customers_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "bicycle_lockers_id", referencedColumnName = "id")})
    private List<BicycleLocker> bicycleLockerList;

    private String name;
}
