package com.himel.eshoppers.service;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.User;

public interface CartService {
    Cart getCartByUser(User currentUser);
    void addProductToCart(String productId, Cart cart);
    void removeProductFromCart(String productId, Cart cart);
    void removeAllProductByProductIdFromCart(String productId, Cart cart);
}
