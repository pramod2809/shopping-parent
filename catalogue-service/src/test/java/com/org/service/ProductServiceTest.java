package com.org.service;

import com.org.model.Product;
import com.org.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class ProductServiceTest {
    ProductService productService;
    @BeforeEach
    public void before(){
        productService = new ProductService();
        productService.productRepository = Mockito.mock(ProductRepository.class);
    }
    @Test
    public void shouldSaveAProductDTO(){
        ProductService.ProductDTO productDTO = ProductService.ProductDTO.builder()
                .name("Product Name")
                .description("Product Description")
                .build();

        Product product = new ProductService.ProductTransformer().tranform(productDTO);

        boolean saved = productService.save(productDTO);
        Assertions.assertThat(saved).isTrue();
        ArgumentCaptor<Product> captor =  ArgumentCaptor.forClass(Product.class);

        Mockito.verify(productService.productRepository, Mockito.times(1)).save(captor.capture());
        Product productReal = captor.getValue();
        Assertions.assertThat(productReal).isEqualTo(product);
    }

}
