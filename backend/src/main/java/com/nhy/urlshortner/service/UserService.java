package com.nhy.urlshortner.service;

import com.nhy.urlshortner.dto.UserDTO;

public interface UserService {
    UserDTO saveUser(UserDTO user);
}
