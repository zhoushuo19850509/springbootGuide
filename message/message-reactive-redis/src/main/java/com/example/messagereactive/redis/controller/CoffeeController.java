package com.example.messagereactive.redis.controller;


import com.example.messagereactive.redis.model.Coffee;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CoffeeController {
    private final ReactiveRedisOperations<String , Coffee> coffeeOps;


    public CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.coffeeOps = coffeeOps;
    }

    @GetMapping("/coffees")
    public Flux<Coffee> all(){
        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get);
    }
}
