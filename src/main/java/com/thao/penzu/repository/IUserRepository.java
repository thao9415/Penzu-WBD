package com.thao.penzu.repository;

import com.thao.penzu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);
}
