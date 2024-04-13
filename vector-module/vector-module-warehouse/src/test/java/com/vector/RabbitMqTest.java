package com.vector;

import com.vector.common.mq.constant.RabbitMqConstant;
import com.vector.warehouse.WarehouseApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = WarehouseApplication.class)
@ExtendWith(SpringExtension.class)
//@ComponentScan(basePackages = "com.vector")
@Slf4j
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void test01() throws InterruptedException {
        log.info("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        Map<String, Object> message = new HashMap<>();
        message.put("bizNo", "001");
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, "purchaseFinished", message);
        TimeUnit.SECONDS.sleep(3L);
    }
}
