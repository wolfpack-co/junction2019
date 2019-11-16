package com.wolfpack.vridgeapi.service;

import java.util.List;

import com.wolfpack.vridgeapi.model.User;
import com.wolfpack.vridgeapi.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserById(int id) {
		User user = userRepository.findById(id);
		if (user == null) {
			throw new IllegalStateException("Cannot find user.");
		}
		return user;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
