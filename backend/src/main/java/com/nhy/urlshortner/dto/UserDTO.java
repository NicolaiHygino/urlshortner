package com.nhy.urlshortner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserDTO {
    @NotNull
    String username;

    @Email
    String email;

    @NotBlank @Size(min = 8)
    String password;
}
