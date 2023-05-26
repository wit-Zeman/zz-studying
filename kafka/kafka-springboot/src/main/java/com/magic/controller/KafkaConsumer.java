package com.magic.controller;

import org.springframework.kafka.annotation.KafkaListener;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-26-13:54
 * @Description:
 */
public class KafkaConsumer {

    @KafkaListener(topics = "first")
    public void consumerTopic(String msg) {
        System.out.println(msg);
    }
}
