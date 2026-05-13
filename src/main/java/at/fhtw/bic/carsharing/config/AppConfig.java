package at.fhtw.bic.carsharing.config;

import at.fhtw.bic.carsharing.persistence.repository.UserRepository;
import at.fhtw.bic.carsharing.service.PasswordEncoder;
import at.fhtw.bic.carsharing.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder();
    }

    @Bean
    public UserRepository getUserRepository() {
        return new UserRepository();
    }

    @Bean
    public UserService getUserService() { return new UserService(getUserRepository(), getPasswordEncoder()); }
}
