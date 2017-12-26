package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.User;

public interface UserService {
    User save(User entity);
    void delete(Long id);
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
