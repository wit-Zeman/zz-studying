package com.magic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-09-9:46
 * @Description:
 */
@RestController
public class TestController {

    // 注入DiscoveryClient
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    private String test(){
        // 做服务发现
        List<ServiceInstance> instances = discoveryClient.getInstances("nacos-client-b");
        return "ok";
    }
}
