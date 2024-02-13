package com.udemy.springbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropsConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer config(){
        Resource resource = new ClassPathResource("externalExample/application.properties");
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(resource);
        return configurer;
    }

}
