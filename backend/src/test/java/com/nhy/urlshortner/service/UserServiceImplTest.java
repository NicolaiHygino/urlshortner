package com.nhy.urlshortner.service;

import com.nhy.urlshortner.domain.User;
import com.nhy.urlshortner.dto.UserDTO;
import com.nhy.urlshortner.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        UserDTO userDTO = new UserDTO("testuser", "test@example.com", "password123");

        // When
        userService.saveUser(userDTO);

        // Then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();

        assertEquals(userDTO.getUsername(), savedUser.getUsername());
        assertEquals(userDTO.getPassword(), savedUser.getPassword());
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
    }

}
