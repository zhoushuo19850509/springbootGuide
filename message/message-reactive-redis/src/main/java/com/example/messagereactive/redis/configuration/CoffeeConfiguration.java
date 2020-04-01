package com.example.messagereactive.redis.configuration;


import com.example.messagereactive.redis.model.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CoffeeConfiguration {

    @Bean
    ReactiveRedisOperations<String, Coffee> redisOperations(ReactiveRedisConnectionFactory factory){

        /**
         * serailizer
         */
        Jackson2JsonRedisSerializer<Coffee> serializer = new Jackson2JsonRedisSerializer<Coffee>(Coffee.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String,Coffee> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String,Coffee> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory,context);

    }
}
