package com.example.restservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/greeting")
                .allowedMethods("GET")
                .allowedOrigins("192.168.0.176")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }
}
