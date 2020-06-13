package com.example.DevDemo.controller;

import com.example.DevDemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    User login() {
        System.out.println("login----->");
        User user = new User();
        user.setTimestamp(System.currentTimeMillis());
        user.setUsername("user-test");
        user.setPassword("123456");
        return user;
    }
}
