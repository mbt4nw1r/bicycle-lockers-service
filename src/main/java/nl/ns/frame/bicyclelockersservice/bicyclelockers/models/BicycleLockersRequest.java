package nl.ns.frame.bicyclelockersservice.bicyclelockers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BicycleLockersRequest {
    private String readableId;
    private String status;
}
