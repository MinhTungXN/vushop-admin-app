package com.vu.service;

import java.util.List;

import com.vu.dto.UserDto;

public interface UserService {

	void addUser(UserDto userDto);
	
	void editUser(Long id, UserDto userDto);
	
	void removeUser(Long userId);
	
	List<UserDto> getUsers();
	
	List<UserDto> getUsersByFullName(String fullname);
	
	UserDto getUserByEmail(String email);
	
	UserDto getUserById(Long userId);
}
