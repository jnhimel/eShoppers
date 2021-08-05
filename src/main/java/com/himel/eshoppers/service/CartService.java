package com.himel.eshoppers.service;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.User;
import com.himel.eshoppers.exceptions.ProductNotFoundException;

public interface CartService {
    Cart getCartByUser(User currentUser);
    void addProductToCart(String productId, Cart cart) throws ProductNotFoundException;
}
