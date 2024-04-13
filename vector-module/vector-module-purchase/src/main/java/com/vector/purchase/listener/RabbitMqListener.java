package com.vector.purchase.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rabbitmq.client.Channel;
import com.vector.common.mq.constant.RabbitMqConstant;
import com.vector.purchase.entity.PurchaseOrder;
import com.vector.purchase.service.PurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class RabbitMqListener {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMqConstant.QUEUE_PURCHASE, durable = "true"),
            exchange = @Exchange(name = RabbitMqConstant.EXCHANGE),
            key = {"purchaseFinished"}
    ))
    public void purchaseFinished(Map<String, Object> body,
                                 Channel channel,
                                 Message message) throws IOException {
        log.info("messageId is {}", message.getMessageProperties().getMessageId());
        log.info("purchaseFinishedBody is '{}'", body);
        PurchaseOrder order = purchaseOrderService.getOne(new LambdaQueryWrapper<>(PurchaseOrder.class)
                .eq(PurchaseOrder::getOrderNo, body.get("bizNo"))
        );
        purchaseOrderService.finished(order.getId());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        channel.basicn
    }

}
