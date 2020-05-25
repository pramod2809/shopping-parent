package com.org.kafka;

import com.org.kafka.model.OrderEntity;
import com.org.kafka.service.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class OrderKafkaProducerApplication implements CommandLineRunner {
    @Value("${app.topic.example}")
    private String topic;

    public static void main(String[] args) {
        SpringApplication.run(OrderKafkaProducerApplication.class, args);
    }

    @Autowired
    private OrderSender sender;

    @Override
    public void run(String... strings) throws Exception {
        OrderEntity orderEntity = new OrderEntity(1,"Product 12345", "Order Name","Order Details",10,"Singapore");
        sender.send(orderEntity);
        System.out.println("Message has sent successfully");
        /*sender.send(topic, new GenericMessage<String>("SENT"));
        System.out.println("Message successfully sent ........");*/
    }

}
