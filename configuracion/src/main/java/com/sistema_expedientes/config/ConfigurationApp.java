package com.sistema_expedientes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApp {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
