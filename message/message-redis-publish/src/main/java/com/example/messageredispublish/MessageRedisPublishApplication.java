package com.example.messageredispublish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

//import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class MessageRedisPublishApplication {

	private static final Logger logger = LoggerFactory.getLogger(MessageRedisPublishApplication.class);

	/**
	 * redis template
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory){
		return new StringRedisTemplate(connectionFactory);

	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(MessageRedisPublishApplication.class, args);

		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);

		logger.info("sending message...");
		template.convertAndSend("chat","hi from spring boot!!!");

		System.exit(0);
	}

}
