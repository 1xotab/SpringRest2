package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    void add(User user);

    void delete(Long id);

    Optional<User> get(Long id);

    List<User> getAllUsers();

    void set(User newUser);

    User loadUserByEmail(String username);
}
