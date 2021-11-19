package com.shf.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redisTest")
    public String testRedis(){
//        设置值到redis
        redisTemplate.opsForValue().set("name","lucy");
//        redis获取值
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }
}
