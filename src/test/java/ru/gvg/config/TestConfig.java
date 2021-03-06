package ru.gvg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gvg.service.UserService;
import ru.gvg.service.UserValidator;

import static org.mockito.Mockito.mock;

/**
 * @author Valeriy Gyrievskikh
 * @since 23.09.2020
 */
@Configuration
public class TestConfig {

    @Bean
    public UserValidator userValidator(){
        return new UserValidator();
    }

    @Bean
    public UserService userService(){
        return mock(UserService.class);
    }
}
