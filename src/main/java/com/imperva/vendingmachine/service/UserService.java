package com.imperva.vendingmachine.service;

import com.imperva.vendingmachine.dto.UserDto;
import com.imperva.vendingmachine.model.User;

public interface UserService {
	User findById(Long id);
	User findByEmail(String email);
	User modifyUser(UserDto userDto, Long userId);
	void updatePassword(String email, String oldPassword, String newPassword);
	User signUp(UserDto userDto);
	void deleteUser(Long userId);
}
