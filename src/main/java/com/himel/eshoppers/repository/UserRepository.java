package com.himel.eshoppers.repository;

import com.himel.eshoppers.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUsername(String username);
}
