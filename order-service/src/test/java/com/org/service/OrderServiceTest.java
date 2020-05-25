package com.org.service;

import com.org.model.OrderEntity;
import com.org.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class OrderServiceTest {
    OrderService orderService;
    @BeforeEach
    public void before(){
        orderService = new OrderService();
        orderService.orderRepository = Mockito.mock(OrderRepository.class);
    }
    @Test
    public void shouldSaveAOrderDTO(){
        OrderService.OrderDTO orderDTO = OrderService.OrderDTO.builder()
                .name("Order Name")
                .description("Order Description")
                .build();

        OrderEntity product = new OrderEntity();

        //Note : This test will fail is other service is not running
        boolean saved = orderService.saveOrder(orderDTO);
        Assertions.assertThat(saved).isTrue();
        ArgumentCaptor<OrderEntity> captor =  ArgumentCaptor.forClass(OrderEntity.class);

        Mockito.verify(orderService.orderRepository, Mockito.times(1)).save(captor.capture());
        OrderEntity productReal = captor.getValue();
        Assertions.assertThat(productReal).isEqualTo(product);
    }

}
