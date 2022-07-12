package com.jiaml.marketing.controller;

import com.jiaml.user.api.dto.User;
import com.jiaml.user.api.feign.UserFeignClient;
import com.jiaml.user.autoconfigure.MsUserConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private final UserFeignClient userFeignClient;

    @Autowired
    MsUserConfiguration msUserConfiguration;

    @Autowired
    public GreetingController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @GetMapping("/hello/{userId}")
    public String sayHi(@PathVariable("userId") Long userId) {
        String url = msUserConfiguration.getUrl();
        logger.info("请求地址：{}", url);

        User user = userFeignClient.getUserById(userId);

        if (user == null || !StringUtils.hasLength(user.getName())) {
            return "Hello, 匿名者";
        }

        return "Hello, " + user.getName();
    }
}
