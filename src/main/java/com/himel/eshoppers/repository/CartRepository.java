package com.himel.eshoppers.repository;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User currentUser);

    Cart save(Cart cart);

    Cart update(Cart cart);
}
