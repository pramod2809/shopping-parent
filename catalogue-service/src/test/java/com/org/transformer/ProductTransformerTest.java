package com.org.transformer;

import com.org.model.Product;
import com.org.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTransformerTest {

    ProductService.ProductTransformer productTransformer;

    @BeforeEach
    public void before(){
        productTransformer = new ProductService.ProductTransformer();
    }

    @Test
    public void shouldTransform(){
        ProductService.ProductDTO productDTO = ProductService.ProductDTO.builder()
                .name("Product Name")
                .description("Product Description")
                .build();

        Product expectedProduct = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .build();

        Product tranformdProduct = productTransformer.tranform(productDTO);
        Assertions.assertThat(tranformdProduct).isEqualTo(expectedProduct);
    }
}
