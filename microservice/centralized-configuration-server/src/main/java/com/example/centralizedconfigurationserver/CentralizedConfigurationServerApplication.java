package com.example.centralizedconfigurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CentralizedConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralizedConfigurationServerApplication.class, args);
	}

}
