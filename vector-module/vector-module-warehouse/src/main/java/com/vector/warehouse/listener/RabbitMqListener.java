package com.vector.warehouse.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vector.common.mq.constant.RabbitMqConstant;
import com.vector.warehouse.entity.WarehouseReceive;
import com.vector.warehouse.enums.WarehouseReceiveStatus;
import com.vector.warehouse.form.WarehouseReceiveForm;
import com.vector.warehouse.service.WarehouseReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RabbitMqListener {

    @Autowired
    private WarehouseReceiveService warehouseReceiveService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMqConstant.QUEUE_WAREHOUSE, durable = "true"),
            exchange = @Exchange(name = RabbitMqConstant.EXCHANGE),
            key = {"receiveLogistics"}
    ))
    public void receiveLogistics(@Payload Map<String, Object> message,
                                  @Header(AmqpHeaders.MESSAGE_ID) String messageId) {
        log.info("messageId is {}", messageId);
        log.info("receiveLogisticsBody is '{}'", message);
        WarehouseReceive receive = warehouseReceiveService.getOne(new LambdaQueryWrapper<>(WarehouseReceive.class)
                .eq(WarehouseReceive::getBizNo, MapUtils.getString(message, "bizNo")));
        receive.setLogisticsName(MapUtils.getString(message, "logisticsName"));
        receive.setLogisticsNo(MapUtils.getString(message, "logisticsNo"));
        receive.setReceiveStatus(WarehouseReceiveStatus.SENT_OUT);
        warehouseReceiveService.updateById(receive);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMqConstant.QUEUE_WAREHOUSE, durable = "true"),
            exchange = @Exchange(name = RabbitMqConstant.EXCHANGE),
            key = {"receiveCreate"}
    ))
    public void receiveCreate(@Payload WarehouseReceiveForm message,
                              @Header(AmqpHeaders.MESSAGE_ID) String messageId) {
        log.info("messageId is {}", messageId);
        log.info("receiveCreateBody is '{}'", message);
        warehouseReceiveService.create(message);
    }
}
