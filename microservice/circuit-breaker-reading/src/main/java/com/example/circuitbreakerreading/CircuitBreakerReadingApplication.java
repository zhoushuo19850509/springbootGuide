package com.example.circuitbreakerreading;

import com.example.circuitbreakerreading.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class CircuitBreakerReadingApplication {

	/**
	 * Service层
	 */
	@Autowired
	private BookService bookService;


	@Bean
	public RestTemplate rest(RestTemplateBuilder builder){
		return builder.build();
	}

	/**
	 * Controller层，调用Service层的方法处理客户端请求
	 * @return
	 */
	@RequestMapping("/to-read")
	public String toRead(){
		return bookService.readingList();
	}

	/**
	 * 启动入口
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerReadingApplication.class, args);
	}


}
