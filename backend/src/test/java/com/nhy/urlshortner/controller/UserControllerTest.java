package com.nhy.urlshortner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhy.urlshortner.dto.UserDTO;
import com.nhy.urlshortner.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveUser_whenValidUserDTO_thenReturnsSavedUserDTO() throws Exception {
        var inputDto = new UserDTO("testuser", "test@example.com", "password123");
        var outputDto = new UserDTO("testuser", "test@example.com", null); // Password should not be returned

        when(userService.saveUser(any(UserDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.email", is("test@example.com")))
                .andExpect(jsonPath("$.password").doesNotExist());


    }

    @Test
    void testSaveUser_whenUsernameIsNull_thenReturnsBadRequest() throws Exception {
        var inputDto = new UserDTO(null, "test@example.com", "password123");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveUser_whenEmailIsInvalid_thenReturnsBadRequest() throws Exception {
        var inputDto = new UserDTO("testuser", "not-an-email", "password123");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveUser_whenPasswordIsTooShort_thenReturnsBadRequest() throws Exception {
        var inputDto = new UserDTO("testuser", "test@example.com", "1234");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }
}