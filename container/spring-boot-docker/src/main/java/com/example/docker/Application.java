package main.java.com.example.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("hello")
    public String hello(){
        return "Hello Docker World!";
    }

    public static void main(String[] args){

        SpringApplication.run(Application.class);
        System.out.println("hello");
    }
}