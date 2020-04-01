package com.example.routingandfiltingbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class RoutingAndFiltingBookApplication {

	@RequestMapping(value = "/available")
	public String available(){
		return "Spring In Action";
	}

	@RequestMapping(value = "/to-checkout")
	public String checkOut(){
		return "Spring Boot In Action";
	}


	public static void main(String[] args) {
		SpringApplication.run(RoutingAndFiltingBookApplication.class, args);
	}



}
