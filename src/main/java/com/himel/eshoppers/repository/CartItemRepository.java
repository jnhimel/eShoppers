package com.himel.eshoppers.repository;

import com.himel.eshoppers.domain.CartItem;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);
    CartItem update(CartItem cartItem);
    void remove(CartItem cartItem);
}
