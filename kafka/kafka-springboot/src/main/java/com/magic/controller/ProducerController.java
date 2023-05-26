package com.magic.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-26-13:47
 * @Description:
 */
@RestController
public class ProducerController {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/data")
    public String data(String msg) {

        kafkaTemplate.send("first", msg);

        return "ok";
    }
}
