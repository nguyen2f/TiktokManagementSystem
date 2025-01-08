package com.ndc.tiktokmanagement.service;

import com.ndc.tiktokmanagement.model.Product;
import com.ndc.tiktokmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateStock(Product product) {
        productRepository.save(product);
    }
    public void checkLowStock(Product product) { }
    public void trackInventory(Product product) { }
}

