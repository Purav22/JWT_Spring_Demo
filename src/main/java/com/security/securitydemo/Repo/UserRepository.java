package com.security.securitydemo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.securitydemo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);


}

