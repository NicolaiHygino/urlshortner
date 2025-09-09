package com.nhy.urlshortner.repository;


import com.nhy.urlshortner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
