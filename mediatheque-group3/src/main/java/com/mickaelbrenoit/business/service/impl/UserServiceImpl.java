package com.mickaelbrenoit.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mickaelbrenoit.business.exception.ServiceException;
import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.repository.UserRepository;
import com.mickaelbrenoit.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User entity) throws ServiceException {
		return userRepository.save(entity);
	}
	
	@Override
	public void delete(Long id) throws ServiceException {
		userRepository.delete(id);
	}
	
	@Override
	public List<User> findAll() throws ServiceException {
		return (List<User>) userRepository.findAll();
	}
	
	@Override
	public User findById(Long id) throws ServiceException {
		return userRepository.findOne(id);
	}
	
	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	
	@Override
	public List<User> findByFirstNameAndLastName(String firstName, String lastName) throws ServiceException {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	
}