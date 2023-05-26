package com.magic.feign.hystrix;

import com.magic.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-05-20:34
 * @Description:
 */
@Component
public class CustomerRentFeignHystrix implements CustomerRentFeign {

    @Override
    public String rentCar() {
        return "备胎方案";
    }
}


