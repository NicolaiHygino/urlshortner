package com.nhy.urlshortner.service;

import com.nhy.urlshortner.domain.User;
import com.nhy.urlshortner.dto.UserDTO;
import com.nhy.urlshortner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Override
    public UserDTO saveUser(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        User savedUser = repository.save(user);
        return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), null);
    }
}
