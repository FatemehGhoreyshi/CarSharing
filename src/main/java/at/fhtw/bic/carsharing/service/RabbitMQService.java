package at.fhtw.bic.carsharing.service;


import at.fhtw.bic.carsharing.config.RabbitMQConfig;
import at.fhtw.bic.carsharing.dto.EmergencyDTO;
import at.fhtw.bic.carsharing.dto.VehicleStatusDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @PostConstruct
    public void init() {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.UPDATE_STATUS_QUEUE,
                "RabbitMQ initialized"
        );
    }
    public void sendVehicleStatus(VehicleStatusDTO status) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.UPDATE_STATUS_QUEUE,
                status
        );
    }

    public void sendEmergency(EmergencyDTO emergency) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMERGENCY_QUEUE,
                emergency
        );
    }
}
