package com.example.clientsideloadbalancingserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
public class ClientSideLoadBalancingServerApplication {

	private static Logger logger = LoggerFactory.getLogger(ClientSideLoadBalancingServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClientSideLoadBalancingServerApplication.class, args);
	}

	@GetMapping("/greeting")
	public String greet(){

		logger.info("start greeting!");

		List<String> greetings = new ArrayList<String>();
		greetings.add("hello hi");
		greetings.add("from Hob");
		greetings.add("drink some water!!");
		Random random = new Random();

		int index = random.nextInt(greetings.size());
		return greetings.get(index);

	}
}
