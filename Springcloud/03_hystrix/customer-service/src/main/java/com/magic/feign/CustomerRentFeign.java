package com.magic.feign;


import com.magic.feign.hystrix.CustomerRentFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-20:16
 * @Description:
 */
@FeignClient(value = "rent-car-service",fallback = CustomerRentFeignHystrix.class)
public interface CustomerRentFeign {

    @RequestMapping("/rentCar")
    String rentCar();
}
