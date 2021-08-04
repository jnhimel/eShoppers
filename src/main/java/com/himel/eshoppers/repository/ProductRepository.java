package com.himel.eshoppers.repository;

import com.himel.eshoppers.dto.ProductDTO;

import java.util.List;

public interface ProductRepository {
    List<ProductDTO> findAllProducts();
}
