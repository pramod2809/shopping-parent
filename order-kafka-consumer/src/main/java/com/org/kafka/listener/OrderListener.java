package com.org.kafka.listener;

import com.org.kafka.model.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
@Service
public class OrderListener {

    private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

    @KafkaListener(topics = "${app.topic.example}")
    public void receive(@Payload OrderEntity data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);
        System.out.println("received data="+data);

        headers.keySet().forEach(key -> {
            LOG.info("{}: {}", key, headers.get(key));
        });
    }

}
