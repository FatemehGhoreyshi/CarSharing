package at.fhtw.bic.carsharing.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queue names
    public static final String UPDATE_STATUS_QUEUE = "UpdateStatus";
    public static final String EMERGENCY_QUEUE = "Emergency";
    public static final String CREATE_INVOICE_QUEUE = "CreateInvoice";

    // Queue Beans
    @Bean
    public Queue updateStatusQueue() {
        return new Queue(UPDATE_STATUS_QUEUE);
    }

    @Bean
    public Queue emergencyQueue() {
        return new Queue(EMERGENCY_QUEUE);
    }

    @Bean
    public Queue createInvoiceQueue() {
        return new Queue(CREATE_INVOICE_QUEUE);
    }

    // RabbitTemplate Bean
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}