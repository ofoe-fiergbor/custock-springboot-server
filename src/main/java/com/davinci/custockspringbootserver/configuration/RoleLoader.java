package com.davinci.custockspringbootserver.configuration;

import com.davinci.custockspringbootserver.domain.model.Role;
import com.davinci.custockspringbootserver.services.interfece.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleLoader {

    @Bean
    CommandLineRunner commandLineRunner(AppUserService appUserService) {
        return args -> {
            appUserService.saveRole(new Role(null, "ROLE_USER"));
            appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
        };
    }
}
