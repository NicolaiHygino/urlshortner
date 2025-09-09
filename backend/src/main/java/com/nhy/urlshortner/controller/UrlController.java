package com.nhy.urlshortner.controller;

import com.nhy.urlshortner.dto.UrlDTO;
import com.nhy.urlshortner.service.UrlServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlServiceImpl urlService;

    @PostMapping()
    UrlDTO saveUrl(@RequestBody @Valid UrlDTO urlDTO) {
        return urlService.saveUrl(urlDTO);
    }
}
