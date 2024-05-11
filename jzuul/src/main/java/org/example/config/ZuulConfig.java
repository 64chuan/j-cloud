package org.example.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@EnableZuulProxy    // 表示开启 Zuul 的代理功能，可以自动注册到 Eureka 服务中心，并集成 Ribbon 和 Hystrix 等组件。
public class ZuulConfig {

}
