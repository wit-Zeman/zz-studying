package com.magic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-20:07
 * @Description:
 */
@RestController
public class rentController {
    @RequestMapping("/rentCar")
    public String rentCar(){
        return "租车成功";
    }
}
