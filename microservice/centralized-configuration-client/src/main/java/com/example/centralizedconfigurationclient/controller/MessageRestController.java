package com.example.centralizedconfigurationclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageRestController {

    @Value("${user.sex : hello1 default }")
    private String message;

    @RequestMapping("/message")
    public String getMessage() {
        return this.message;
    }
}
