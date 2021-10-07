package com.imperva.vendingmachine.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.dto.UserDto;
import com.imperva.vendingmachine.model.Role;
import com.imperva.vendingmachine.model.Status;
import com.imperva.vendingmachine.model.User;
import com.imperva.vendingmachine.repository.RoleRepository;
import com.imperva.vendingmachine.repository.UserRepository;
import com.imperva.vendingmachine.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public UserServiceImpl() {
	
	}
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The user with id " + id + " does not exist."));
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).
				orElseThrow(() -> new IllegalArgumentException("The user with email " + email + " does not exist."));
	}
	
	@Override
	public User modifyUser(UserDto userDto, Long userId) {
		User userFromDb = findById(userId);
		userFromDb.setFirstName(userDto.getFirstName());
		userFromDb.setLastName(userDto.getLastName());
		userFromDb.setEmail(userDto.getEmail());
		return userRepository.save(userFromDb);
	}
	
	@Override
	@SneakyThrows
	public void updatePassword(String email, String oldPassword, String newPassword) {
		String encodedOldPassword = bCryptPasswordEncoder.encode(oldPassword);
		User user = findByEmail(email);
		
		if(encodedOldPassword.equals(user.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(newPassword));
			userRepository.save(user);
		} else {
			log.info("User enetered wrong password. Password in DB and entered password are not equals");
			throw new Exception("Old password and new password are not equals");
		}
	}
	
	@Override
	public User signUp(UserDto userDto) {
		Role userRole;
		Optional<User> user = userRepository.findByEmail(userDto.getEmail());
		if (!user.isPresent()) {
				userRole = roleRepository.findByRole(UserRoles.ADMIN);
		} else {
			throw new RuntimeException("User with email: " + userDto.getEmail() + " already signed up");
		}
		
		User created = User.builder()
				.email(userDto.getEmail())
				.status(Status.ACTIVE)
				.password(bCryptPasswordEncoder.encode(userDto.getPassword()))
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.build();
		
		return userRepository.save(created);
	}
	
	@Override
	public void deleteUser(final Long userId) {
		Optional<User> user = userRepository.findById(userId);
		user.ifPresent(value -> userRepository.delete(value));
	}
}
