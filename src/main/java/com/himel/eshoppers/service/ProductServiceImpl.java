package com.himel.eshoppers.service;

import com.himel.eshoppers.domain.Product;
import com.himel.eshoppers.dto.ProductDTO;
import com.himel.eshoppers.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAllProductSortedByName() {
        return productRepository.findAllProducts()
                .stream()
                .map(this::convertToDTO)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
