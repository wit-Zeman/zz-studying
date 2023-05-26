package com.magic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-06-11:45
 * @Description:
 */
@RestController
@Slf4j
public class LoginController {

    @RequestMapping("/user/doLogin")
    public String doLogin(String name, String pwd) {
        log.info(name);
        log.info(pwd);
        String token = UUID.randomUUID().toString();
        return token;
    }
}
