package com.nikita.inventorymanagement.service;

import com.nikita.inventorymanagement.dto.ProductRequestDto;
import com.nikita.inventorymanagement.model.Product;
import com.nikita.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDetails.getName());

        product.setQuantity(productDetails.getQuantity());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductRequestDto addProduct(ProductRequestDto productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setQuantity(productDTO.getQuantity());

        // 2. Save to DB
        Product savedProduct = productRepository.save(product);

        // 3. Convert Entity → DTO (optional, to return saved details)
        ProductRequestDto responseDTO = new ProductRequestDto();
        responseDTO.setName(savedProduct.getName());
        responseDTO.setQuantity(savedProduct.getQuantity());

        return responseDTO;
    }
}
