package com.magic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        ops.put("1","name","magic");
        ops.put("2","age","18");

        System.out.println(ops.get("1","name"));

    }

}
