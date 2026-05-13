package at.fhtw.bic.carsharing.persistence.repository;

import at.fhtw.bic.carsharing.persistence.entity.GeoCoord;
import at.fhtw.bic.carsharing.persistence.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class VehicleRepository {
    private static long nextId = 1L;
    public static long nextId() { return nextId++; }
    
    
    private final Map<Long, Vehicle> vehicles;


    public VehicleRepository() {
        vehicles = new HashMap<>();

        vehicles.put(1L, new Vehicle(1L, "VW ID.3", "Standard Edition", new GeoCoord(16.376941, 48.239177), 0, null) );
        vehicles.put(2L, new Vehicle(2L, "VW ID.5", "Luxury Edition", new GeoCoord(16.377488, 48.240183), 0, null) );
        nextId = 3L;
    }
    
    
     // CRUD Operations:
    public Vehicle save(Vehicle Vehicle) {
        if (Vehicle.getId()==null)
            Vehicle.setId( nextId() );
        vehicles.put( Vehicle.getId(), Vehicle );
        return Vehicle;
    }

    public List<Vehicle> findAll() {
        return vehicles.values().stream().toList();
    }

    public Optional<Vehicle> findById(long id) {
        return Optional.ofNullable(vehicles.get(id));
    }

    public Optional<Vehicle> findByName(String name) {
        for ( Vehicle Vehicle : vehicles.values() ) {
            if ( Vehicle.getName().equalsIgnoreCase( name ) )
                return Optional.of(Vehicle);
        }
        return Optional.empty();
    }


    public Vehicle deleteById(long id) {
        return vehicles.remove(id);
    }
   
}
