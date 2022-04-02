package com.vector.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zorg
 * @date 2022/3/24
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.vector.wms.mapper")
@EnableFeignClients(basePackages = "com.vector.*.api")
@ComponentScan(basePackages = "com.vector")
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}
