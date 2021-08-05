package com.himel.eshoppers.repository;

import com.himel.eshoppers.domain.Product;
import com.himel.eshoppers.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();

    Optional<Product> findById(Long productId);
}
