package at.fhtw.bic.carsharing.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;    // managed by VehicleRepository

    private String name;
    private String description;

    private GeoCoord position;
    private int state;  // 0..free; 1..occupied; 2..out-of-order
    private Long currentUserId;
}
