package com.nhy.urlshortner.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

@Value
public class UrlDTO {
    @URL
    String longUrl;

    @NotNull
    Long userId;
}
