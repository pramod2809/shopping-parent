package com.org.service;

import com.org.model.Product;
import com.org.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public boolean save(ProductDTO productDTO){
        Product product = new ProductTransformer().tranform(productDTO);
        productRepository.save(product);
        return true;
    }

    public boolean isExists(String name){
        return productRepository.findByName(name).isPresent();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDTO{
        String name;
        String description;

        public boolean isValid() {
            return false;
        }
    }

    public static class ProductTransformer {
        public Product tranform(ProductDTO productDTO) {
            return Product.builder()
                    .name(productDTO.getName())
                    .description(productDTO.getDescription())
                    .build();
        }

        public ProductDTO tranform(Product product) {
            return ProductDTO.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .build();
        }
    }
}
