package com.himel.eshoppers.service;

import com.himel.eshoppers.domain.User;
import com.himel.eshoppers.dto.LoginDTO;
import com.himel.eshoppers.dto.UserDTO;
import com.himel.eshoppers.exceptions.UserNotFoundException;

public interface UserService {
    void saveUser(UserDTO userDTO);
    String encryptPassword(String password);
    boolean isNotUniqueUsername(UserDTO user);
    User verifyUser(LoginDTO loginDTO);
}
