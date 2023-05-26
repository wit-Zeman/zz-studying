package com.magic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-06-12:05
 * @Description:
 */

@RestController
public class RegisterController {
    @RequestMapping("/user/register")
    public String register(){

        return "注册成功";
    }
}
