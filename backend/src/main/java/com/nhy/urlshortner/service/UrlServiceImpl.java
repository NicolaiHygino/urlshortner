package com.nhy.urlshortner.service;

import com.nhy.urlshortner.domain.Url;
import com.nhy.urlshortner.domain.User;
import com.nhy.urlshortner.dto.UrlDTO;
import com.nhy.urlshortner.repository.UrlRepository;
import com.nhy.urlshortner.repository.UserRepository;
import com.nhy.urlshortner.util.Base62generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements  UrlService{
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    @Override
    public UrlDTO saveUrl(UrlDTO urlDTO) {
        Url url = new Url();

        url.setLongUrl(urlDTO.getLongUrl());
        url.setShortCode(Base62generator.generateRandom(10));

        User userReference = userRepository.getReferenceById(urlDTO.getUserId());
        url.setUser(userReference);

        Url savedUrl = urlRepository.save(url);
        return new UrlDTO(savedUrl.getLongUrl(), savedUrl.getId());
    }
}
