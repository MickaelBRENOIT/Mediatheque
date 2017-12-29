package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.User;

public interface UserService {
    User save(User entity);
    void delete(Long idUser);
    List<User> findAll();
    User findById(Long idUser);
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
	List<User> findAllByRole(String string);
}
