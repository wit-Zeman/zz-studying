package com.magic.controller;

import com.magic.CustomerServiceApplication;
import com.magic.feign.CustomerRentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-20:15
 * @Description:
 */
@RestController
public class customerController {

    @Resource
    private CustomerRentFeign customerRentFeign;

    @RequestMapping("/rent")
    public String rent() {
        String res = customerRentFeign.rentCar();
        return res;
    }
}
