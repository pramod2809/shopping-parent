package com.org.kafka.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    Integer id;
    String userId;
    String name;
    String description;
    Integer quantity;
    String address;

    @Override
    public String toString() {
        return "OrderEntity{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", quantity=" + quantity +
            ", address='" + address + '\'' +
            '}';
    }
}

