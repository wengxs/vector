package com.vector.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zorg
 * @date 2022/3/24
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.vector.base.mapper")
//@ComponentScan(basePackages = "com.vector")
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
