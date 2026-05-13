package at.fhtw.bic.carsharing.controller;

import at.fhtw.bic.carsharing.dto.EmergencyDTO;
import at.fhtw.bic.carsharing.dto.VehicleStatusDTO;
import at.fhtw.bic.carsharing.service.RabbitMQService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final RabbitMQService rabbitMQService;

    public DeviceController(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/{vehicleId}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long vehicleId,
            @RequestBody VehicleStatusDTO statusDTO
    ) {

        rabbitMQService.sendVehicleStatus(statusDTO);

        return ResponseEntity.ok("Vehicle status sent to queue");
    }

    @PostMapping("/{vehicleId}/alarm")
    public ResponseEntity<String> sendAlarm(
            @PathVariable Long vehicleId,
            @RequestBody EmergencyDTO emergencyDTO
    ) {

        rabbitMQService.sendEmergency(emergencyDTO);

        return ResponseEntity.ok("Emergency sent to queue");
    }
}