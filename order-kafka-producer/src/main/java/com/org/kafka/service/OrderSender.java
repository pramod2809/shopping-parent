package com.org.kafka.service;

import com.org.kafka.model.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSender {
    private static final Logger LOG = LoggerFactory.getLogger(OrderSender.class);

    @Autowired
    private KafkaTemplate<String, OrderEntity> kafkaTemplate;

    @Value("${app.topic.example}")
    private String topic;

    public void send(OrderEntity data){
        LOG.info("sending data='{}' to topic='{}'", data, topic);

        Message<OrderEntity> message = MessageBuilder
            .withPayload(data)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build();

        kafkaTemplate.send(message);
        kafkaTemplate.flush();
    }
}
