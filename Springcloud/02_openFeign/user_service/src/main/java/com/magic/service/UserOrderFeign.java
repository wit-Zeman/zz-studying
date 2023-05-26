package com.magic.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-16:41
 * @Description:
 */
@FeignClient(value = "order-service")
public interface UserOrderFeign {

    @RequestMapping("/orderService")
    String doOrder();
}
