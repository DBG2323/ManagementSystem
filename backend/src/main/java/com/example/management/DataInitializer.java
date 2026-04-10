package com.example.management;

import com.example.management.entity.User;
import com.example.management.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initUsers(UserService userService) {
        return args -> {
            if (userService.findByUsername("admin") == null) {
                userService.save(new User("admin", "admin", "admin@example.com"));
                userService.save(new User("user01", "123456", "user01@example.com"));
                userService.save(new User("user02", "123456", "user02@example.com"));
            }
        };
    }
}