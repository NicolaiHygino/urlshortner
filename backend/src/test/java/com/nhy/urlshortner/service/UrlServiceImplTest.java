package com.nhy.urlshortner.service;


import com.nhy.urlshortner.domain.Url;
import com.nhy.urlshortner.domain.User;
import com.nhy.urlshortner.dto.UrlDTO;
import com.nhy.urlshortner.repository.UrlRepository;
import com.nhy.urlshortner.repository.UserRepository;
import com.nhy.urlshortner.util.Base62generator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UrlServiceImplTest {
    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UrlServiceImpl urlService;

    @Test
    void saveUrl_shouldSaveUrlWithCorrectData() {
        // Given
        var urlDto = new UrlDTO("https://www.nicolai.com", 1L);
        var user = new User();
        user.setId(1L);
        var shortCode = "shortCode";
        var url = new Url();
        url.setLongUrl(urlDto.getLongUrl());
        url.setUser(user);
        url.setShortCode(shortCode);
        url.setId(1L);


        try (MockedStatic<Base62generator> mocked = mockStatic(Base62generator.class)) {
            mocked.when(() -> Base62generator.generateRandom(10)).thenReturn(shortCode);
            Mockito.when(userRepository.getReferenceById(urlDto.getUserId())).thenReturn(user);
            Mockito.when(urlRepository.save(Mockito.any(Url.class))).thenReturn(url);

            // When
            urlService.saveUrl(urlDto);

            // Then
            ArgumentCaptor<Url> urlArgumentCaptor = ArgumentCaptor.forClass(Url.class);
            verify(urlRepository).save(urlArgumentCaptor.capture());

            var capturedUrl = urlArgumentCaptor.getValue();
            Assertions.assertThat(capturedUrl.getLongUrl()).isEqualTo(urlDto.getLongUrl());
            Assertions.assertThat(capturedUrl.getUser()).isEqualTo(user);
            Assertions.assertThat(capturedUrl.getShortCode()).isEqualTo(shortCode);
        }
    }

    @Test
    void getOriginalUrl_shouldReturnLongUrl_whenShortCodeExists() {
        // Given
        var shortCode = "shortCode";
        var longUrl = "https://www.nicolai.com";
        var url = new Url();
        url.setLongUrl(longUrl);
        url.setShortCode(shortCode);

        Mockito.when(urlRepository.findByShortCode(shortCode)).thenReturn(url);

        // When
        var result = urlService.getOriginalUrl(shortCode);

        // Then
        Assertions.assertThat(result).isEqualTo(longUrl);
    }

    @Test
    void getOriginalUrl_shouldThrowException_whenShortCodeDoesNotExist() {
        // Given
        var shortCode = "nonExistent";
        Mockito.when(urlRepository.findByShortCode(shortCode)).thenReturn(null);

        // When & Then
        Assertions.assertThatThrownBy(() -> urlService.getOriginalUrl(shortCode))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Url not found for short code: " + shortCode);
    }
}