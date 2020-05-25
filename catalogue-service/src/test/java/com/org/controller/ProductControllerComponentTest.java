package com.org.controller;

import com.org.repository.ProductRepository;
import com.org.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class ProductControllerComponentTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void shouldSaveAProduct() throws Exception {
        ProductService.ProductDTO productDTO = ProductService.ProductDTO.builder()
                .name("Some Name")
                .description("Some Description")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(productDTO);


        mockMvc.perform(MockMvcRequestBuilders.post("/product")
            .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(valueAsString)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                //Print the payload
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldExistProduct(){
        //TODO
    }
}
