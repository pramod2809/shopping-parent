package com.org.model;

import org.junit.jupiter.api.Test;

import javax.persistence.criteria.Order;

import static org.junit.jupiter.api.Assertions.*;

class OrderEntityTest {

    @Test
    public void shouldUseGetterAndSetter(){
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setName("Order1");
        orderEntity.setUserId("A123");
        orderEntity.setAddress("Singapore");
        orderEntity.setQuantity(10);
        orderEntity.setDescription("Order1 for me");

        orderEntity.getId();
        orderEntity.getName();
        orderEntity.getUserId();
        orderEntity.getAddress();
        orderEntity.getQuantity();
        orderEntity.getDescription();

        new OrderEntity(1,"Order1","A123","Singapore",10,"Order1 for me");
        new OrderEntity();

        OrderEntity orderEntityUsingBuilder=OrderEntity.builder()
            .id(1)
            .name("Order1")
            .address("Singapore")
            .userId("A123")
            .quantity(10)
            .description("Order1 for Man")
            .build();
        System.out.println(orderEntity);
    }

}
