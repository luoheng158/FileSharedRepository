package com.example.DevDemo.controller;

import com.example.DevDemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LoginController {

    private final AtomicLong userIdGenerator = new AtomicLong();
    private final List<User> userInfo;
    private final Object lock = new Object();

    public LoginController() {
        System.out.println(threadInfo() + ", LoginController Constructor, hashCode: " + hashCode());
        userInfo = new ArrayList<>();
    }

    @RequestMapping("/login")
    public User login() {
        System.out.println("login----->");
        User user = new User();
        user.setTimestamp(System.currentTimeMillis());
        user.setUsername("user-test");
        user.setPassword("123456");
        return user;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        System.out.println("tid:" + Thread.currentThread().getId() + ", /user/add: " + user);
        synchronized (lock) {
            User findUser = findByUserIdImpl(user.getUserId());
            if (findUser != null) {
                return findUser;
            }
            user.setTimestamp(System.currentTimeMillis());
            user.setUserId(userIdGenerator.incrementAndGet());
            userInfo.add(user);
            return user;
        }
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public User delete(@RequestBody User user) {
        System.out.println(threadInfo() + ", /user/delete: " + user);
        synchronized (lock) {
            final User findUser;
            if ((findUser = findByUserIdImpl(user.getUserId())) == null) {
                return null;
            }
            userInfo.remove(findUser);
            return findUser;
        }
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public User update(@RequestBody User user) {
        System.out.println(threadInfo() + ", /user/update: " + user);
        synchronized (lock) {
            final User findUser;
            if ((findUser = findByUserIdImpl(user.getUserId())) == null) {
                return null;
            }
            findUser.setUsername(user.getUsername());
            findUser.setPassword(user.getPassword());
            return findUser;
        }
    }

    @RequestMapping(value = "/user/query", method = RequestMethod.GET)
    public User findByUserId(@RequestParam long userId) {
        System.out.println(threadInfo() + ", /user/query: " + userId);
        return findByUserIdImpl(userId);
    }

    private User findByUserIdImpl(long userId) {
        synchronized (lock) {
            for (User user : userInfo) {
                if (user.getUserId() == userId) {
                    return user;
                }
            }
            return null;
        }
    }

    private String threadInfo() {
        final Thread thread = Thread.currentThread();
        return String.format("[tid: %s, name: %s]", thread.getId(), thread.getName());
    }

}
