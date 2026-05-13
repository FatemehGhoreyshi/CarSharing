package at.fhtw.bic.carsharing.controller;

import at.fhtw.bic.carsharing.persistence.entity.Vehicle;
import at.fhtw.bic.carsharing.persistence.repository.VehicleRepository;
import at.fhtw.bic.carsharing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/vehicles")
@Slf4j
public class VehicleController {
    private final UserService userService;
    private final VehicleRepository vehicleRepository;

    public VehicleController(UserService userService, VehicleRepository vehicleRepository) {
        this.userService = userService;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping("")
    public ResponseEntity<Vehicle> createVehicle(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                 @RequestBody Vehicle vehicle) {
        var user = userService.getAuthenticatedUserByAuthHeader(auth);
        if ( user == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if ( !user.isRoleFleetManager() )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        vehicle = vehicleRepository.save(vehicle);
        log.info("Created vehicle {}", vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Vehicle>> getVehicles(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        if ( userService.getAuthenticatedUserByAuthHeader(auth) == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(vehicleRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehiclesById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                         @PathVariable Long id) {
        if ( userService.getAuthenticatedUserByAuthHeader(auth) == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        var vehicle = vehicleRepository.findById(id);
        if ( vehicle.isEmpty() )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                 @RequestBody Vehicle vehicle,
                                                 @PathVariable Long id) {
        var user = userService.getAuthenticatedUserByAuthHeader(auth);
        if ( user == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if ( !user.isRoleFleetManager() )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        vehicle.setId(id);  // just to be sure
        vehicle = vehicleRepository.save(vehicle);
        log.info("Updated vehicle {}", vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                              @PathVariable Long id) {
        var user = userService.getAuthenticatedUserByAuthHeader(auth);
        if ( user == null )
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if ( !user.isRoleFleetManager() )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        var vehicle = vehicleRepository.deleteById(id);
        if ( vehicle == null )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
