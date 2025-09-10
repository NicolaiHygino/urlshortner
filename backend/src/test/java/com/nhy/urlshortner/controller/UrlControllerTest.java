package com.nhy.urlshortner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhy.urlshortner.dto.UrlDTO;
import com.nhy.urlshortner.service.UrlServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UrlController.class)
class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UrlServiceImpl urlService;

    @Test
    void testSaveUrl_whenValidUrlDTO_thenReturnsSavedUrlDTO() throws Exception {
        var inputDto = new UrlDTO("https://example.com", 1L);
        var outputDto = new UrlDTO("https://example.com", 1L); // In a real scenario, this might have a shortUrl

        when(urlService.saveUrl(any(UrlDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.longUrl", is("https://example.com")))
                .andExpect(jsonPath("$.userId", is(1)));
    }

    @Test
    void testSaveUrl_whenInvalidUrl_thenReturnsBadRequest() throws Exception {
        var inputDto = new UrlDTO("not-a-valid-url", 1L);

        mockMvc.perform(post("/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveUrl_whenUserIdIsNull_thenReturnsBadRequest() throws Exception {
        var inputDto = new UrlDTO("https://example.com", null);

        mockMvc.perform(post("/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRedirectToOriginalUrl() throws Exception {
        var shortCode = "abcdef";
        var longUrl = "https://example.com";

        when(urlService.getOriginalUrl(shortCode)).thenReturn(longUrl);

        mockMvc.perform(get("/urls/{shortCode}", shortCode))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(longUrl));
    }
}