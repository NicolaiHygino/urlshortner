package com.nhy.urlshortner.service;

import com.nhy.urlshortner.domain.User;
import com.nhy.urlshortner.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO user);
}
