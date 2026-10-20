package at.fhtw.bic.carsharing.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
@Configuration
public class RabbitMQConfig {

    // Queue names
    public static final String UPDATE_STATUS_QUEUE = "UpdateStatus";
    public static final String EMERGENCY_QUEUE = "Emergency";
    public static final String CREATE_INVOICE_QUEUE = "CreateInvoice";

    @Bean
    public Queue updateStatusQueue() {
        return new Queue(UPDATE_STATUS_QUEUE, false);
    }

    @Bean
    public Queue emergencyQueue() {
        return new Queue(EMERGENCY_QUEUE, false);
    }

    @Bean
    public Queue createInvoiceQueue() {
        return new Queue(CREATE_INVOICE_QUEUE, false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}