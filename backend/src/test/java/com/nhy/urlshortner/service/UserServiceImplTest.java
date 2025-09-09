package com.nhy.urlshortner.service;

import com.nhy.urlshortner.domain.User;
import com.nhy.urlshortner.dto.UserDTO;
import com.nhy.urlshortner.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser_shouldSAveUserWithCorrectData() {
        // Given
        var userDTO = new UserDTO("testuser", "test@example.com", "password123");
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // When
        userService.saveUser(userDTO);

        // Then
        var userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userCaptor.capture());

        var savedUser = userCaptor.getValue();

        assertEquals(userDTO.getUsername(), savedUser.getUsername());
        assertEquals(userDTO.getPassword(), savedUser.getPassword());
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
    }

}
