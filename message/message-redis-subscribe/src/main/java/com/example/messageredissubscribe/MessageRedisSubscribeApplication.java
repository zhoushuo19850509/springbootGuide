package com.example.messageredissubscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class MessageRedisSubscribeApplication {

	private static final Logger logger = LoggerFactory.getLogger(MessageRedisSubscribeApplication.class);


	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter,new PatternTopic("chat"));
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver){
		return new MessageListenerAdapter(receiver,"receiveMessage");
	}

	@Bean
	Receiver receiver(CountDownLatch latch){
		return new Receiver(latch);
	}

	@Bean
	CountDownLatch latch(){
		return new CountDownLatch(1);
	}

	public static void main(String[] args) throws InterruptedException {

		/**
		 * subscribe工程的运行模式1
		 * 模拟一个网站运行，可以不断 接收来自redis的消息
		 */
		SpringApplication.run(MessageRedisSubscribeApplication.class, args);

		/**
		 * subscribe工程的运行模式2
		 * 模拟单次接收来自redis的消息，通过CountDownLatch，进行线程间的调度。一旦接收到来自redis的消息，程序马上退出。
		 */
//		ApplicationContext ctx = SpringApplication.run(MessageRedisSubscribeApplication.class, args);
//
//		CountDownLatch latch = ctx.getBean(CountDownLatch.class);
//
//		latch.await();

	}

}
