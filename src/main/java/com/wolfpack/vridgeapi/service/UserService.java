package com.wolfpack.vridgeapi.service;

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
		return userRepository.getOne(id);
	}
}
