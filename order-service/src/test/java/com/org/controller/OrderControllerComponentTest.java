package com.org.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.repository.OrderRepository;
import com.org.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerComponentTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void shouldSaveAProduct() throws Exception {
        OrderService.OrderDTO orderDTO = OrderService.OrderDTO.builder()
                .name("Some Name")
                .description("Some Description")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(orderDTO);

        // Other service should up  otherwise test Fail
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
            .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(valueAsString)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                //Print the payload
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldExistOrder(){
        //TODO
    }
}
