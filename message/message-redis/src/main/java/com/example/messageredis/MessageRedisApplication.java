package com.example.messageredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class MessageRedisApplication {

	private static final Logger logger = LoggerFactory.getLogger(MessageRedisApplication.class);

	/**
	 * message listener container
	 * @param connectionFactory 用于和Redis连接
	 * @param listenerAdapter
	 * @return
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter,new PatternTopic("chat"));
		return container;
	}

	/**
	 * 设置Message Listener
	 * 需要特别注意，我们实例化MessageListenerAdapter对象的时候，要关注constructor方法的第二个参数：
	 * public MessageListenerAdapter(Object delegate, String defaultListenerMethod) {
	 * 这个参数是Receiver中定义的方法名称，不能随便修改！
	 * @param
	 * @return
	 */
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
		ApplicationContext ctx = SpringApplication.run(MessageRedisApplication.class, args);

		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
		CountDownLatch latch = ctx.getBean(CountDownLatch.class);

		logger.info("sending message...");
		template.convertAndSend("chat","hello from spring boot!!!");

		latch.await();

		System.exit(0);

	}

}
