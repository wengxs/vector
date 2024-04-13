package com.vector.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.vector")
@EnableFeignClients(basePackages = "com.vector.*.api")
public class PurchaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchaseApplication.class, args);
    }

//    @Bean
//    public Customizer<SentinelCircuitBreakerFactory> defaultConfig() {
//        return factory -> {
//            factory.configureDefault(
//                    id -> new SentinelConfigBuilder().resourceName(id)
//                            .rules(Collections.singletonList(new DegradeRule(id)
//                                    .setGrade(RuleConstant.DEGRADE_GRADE_RT).setCount(100)
//                                    .setTimeWindow(10)))
//                            .build());
//        };
//    }
}