package com.zr.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 认证服务
 * 申请授权码-有了授权码-去授权中心-获得令牌-携带令牌访问资源
 */

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zr.dao")
@EntityScan("com.zr")//扫描实体类
@ComponentScan(basePackages={"com.zr"})//扫描接口
@SpringBootApplication
public class UcenterAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterAuthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
