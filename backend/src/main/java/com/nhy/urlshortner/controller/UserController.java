package com.nhy.urlshortner.controller;

import com.nhy.urlshortner.dto.UserDTO;
import com.nhy.urlshortner.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    UserDTO postUser(@RequestBody @Valid UserDTO dto) {
        return userService.saveUser(dto);
    }
}
