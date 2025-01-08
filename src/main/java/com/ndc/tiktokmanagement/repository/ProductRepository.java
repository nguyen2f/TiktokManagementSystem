package com.ndc.tiktokmanagement.repository;

import com.ndc.tiktokmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
