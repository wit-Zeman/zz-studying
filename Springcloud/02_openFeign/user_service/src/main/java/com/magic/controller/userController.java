package com.magic.controller;

import com.magic.service.UserOrderFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-16:38
 * @Description:
 */
@RestController
public class userController {

    @Resource
    private UserOrderFeign userOrderFeign;

    /**
     * 消费者 user-service --通过openFeign-->>>  生产者 order-service
     * @return
     */
    @RequestMapping("/userService")
    public String doUserOrder(){
        String result = userOrderFeign.doOrder();
        return result;
    }
}
