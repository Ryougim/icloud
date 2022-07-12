package com.jiaml.user.controller;

import com.jiaml.user.api.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private static final Map<Long, User> users = new HashMap<>();

    static {
        users.put(10000L, new User(10000L, "张三"));
        users.put(10001L, new User(10001L, "李四"));
    }

    @GetMapping("/get/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return users.get(userId);
    }
}
