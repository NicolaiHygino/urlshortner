package com.nhy.urlshortner.repository;

import com.nhy.urlshortner.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
