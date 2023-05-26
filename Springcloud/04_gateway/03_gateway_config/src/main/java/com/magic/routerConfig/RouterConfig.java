package com.magic.routerConfig;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-06-14:18
 * @Description:
 */

//路由配置类
@Configuration
public class RouterConfig {

    /**
     * 注入到spring容器中
     * builder.routers.router(id,path().uri)
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("bilibil",r->r.path("/anime").uri("https://bilibili.com"))
                .build();

    }

}
