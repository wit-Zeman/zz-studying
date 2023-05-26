package com.magic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-16:33
 * @Description:
 */
@RestController
public class orderController {
    @RequestMapping("/orderService")
    public String doOrder() {
        return "买个热干面";
    }
}
