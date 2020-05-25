package com.org.repository;

import com.org.model.OrderEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void shouldSaveAOrder(){
        OrderEntity orderEntity = OrderEntity.builder()
                .userId("A1")
                .name("Order Name")
                .description("Order Description")
                .quantity(10)
                .build();
        orderRepository.save(orderEntity);
        List<OrderEntity> allProduct = orderRepository.findAll();
        Optional<OrderEntity> firstProduct = allProduct.stream().findFirst();
        Assertions.assertThat(firstProduct.isPresent()).isTrue();
        Assertions.assertThat(firstProduct.get()).isEqualTo(orderEntity);

    }

    @Test
    public void shouldSaveManyOrder(){
        OrderEntity firstOrderEntity = OrderEntity.builder()
            .userId("A1")
            .name("Order Name")
            .description("Order Description")
            .quantity(2)
            .build();
        OrderEntity secondOrderEntity = OrderEntity.builder()
            .userId("A2")
            .name("Order Name")
            .description("Order Description")
            .quantity(2)
            .build();
        orderRepository.saveAll(Arrays.asList(firstOrderEntity, secondOrderEntity));
        List<OrderEntity> all = orderRepository.findAll();
        Assertions.assertThat(all).size().isEqualTo(2);

    }

    @Test
    public void shouldSearchByName(){
        OrderEntity firstOrderEntity = OrderEntity.builder()
            .userId("A1")
            .name("Order Name")
            .description("Order Description")
            .quantity(10)
            .build();
        orderRepository.save(firstOrderEntity);
        Optional<OrderEntity> byName = orderRepository.findByName(firstOrderEntity.getName());
        Assertions.assertThat(byName).isNotEmpty();
        Assertions.assertThat(byName.get()).isEqualTo(firstOrderEntity);
    }

}
