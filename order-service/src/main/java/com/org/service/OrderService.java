package com.org.service;

import com.org.model.OrderEntity;
import com.org.repository.OrderRepository;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    public boolean saveOrder(OrderDTO orderDTO){
        ResponseEntity<Boolean> forEntity = restTemplate.getForEntity("http://catalogue-service/product?name=" + orderDTO.getName(), Boolean.class);
        if(forEntity.getBody()){
            OrderEntity order = new OrderMapper().map(orderDTO);
            return orderRepository.save(order)!=null;
        }
        return false;

    }

    public boolean isExists(String name){
        return orderRepository.findByName(name).isPresent();
    }

    private class OrderMapper {
        public OrderEntity map(OrderDTO orderDTO) {
            return OrderEntity.builder()
                    .address(orderDTO.getAddress())
                    .description(orderDTO.getDescription())
                    .name(orderDTO.getName())
                    .quantity(orderDTO.getQuantity())
                    .userId(orderDTO.getUserId())
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderDTO {

        String userId;

        String name;

        String description;

        Integer quantity;

        String address;

        public boolean isValid() {
            return StringUtils.isNotEmpty(userId) && true;
        }
    }
}
