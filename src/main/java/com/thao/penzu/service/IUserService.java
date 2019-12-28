package com.thao.penzu.service;

import com.thao.penzu.model.User;

import java.util.Optional;

public interface IUserService {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);

    Optional<User> findById(Long id);

    void save(User user);

    Iterable<User> findAll();

    void delete(Long id);


}
