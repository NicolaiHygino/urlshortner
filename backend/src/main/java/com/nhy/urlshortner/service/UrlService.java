package com.nhy.urlshortner.service;

import com.nhy.urlshortner.dto.UrlDTO;

public interface UrlService {
    UrlDTO saveUrl(UrlDTO urlDTO);
}
