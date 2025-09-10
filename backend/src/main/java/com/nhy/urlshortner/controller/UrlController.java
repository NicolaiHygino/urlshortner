package com.nhy.urlshortner.controller;

import com.nhy.urlshortner.domain.Url;
import com.nhy.urlshortner.dto.UrlDTO;
import com.nhy.urlshortner.service.UrlServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlServiceImpl urlService;

    @PostMapping()
    UrlDTO saveUrl(@RequestBody @Valid UrlDTO urlDTO) {
        return urlService.saveUrl(urlDTO);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String shortCode) {
        var headers = new HttpHeaders();
        String longUrl = urlService.getOriginalUrl(shortCode);
        headers.add("Location", longUrl);
        return new ResponseEntity<>("Redirecting to pointed url", headers, HttpStatus.FOUND);
    }
}
