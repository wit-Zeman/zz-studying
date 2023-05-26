package com.magic.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-07-14:56
 * @Description:
 */
@Component
@Slf4j
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public void test() {
        // 1、生成uuid 模拟token
        String token = UUID.randomUUID().toString();
        log.info(token);
        // 2、将token写入redis
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("token", token);
        // 3、取出token
        String redisToken = ops.get("token");
        log.info(redisToken);
    }

}
