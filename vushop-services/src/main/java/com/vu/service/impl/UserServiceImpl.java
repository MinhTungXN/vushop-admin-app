package com.vu.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.vu.constant.MessageConstant;
import com.vu.constant.PropertyConstant;
import com.vu.dto.UserDto;
import com.vu.entity.Role;
import com.vu.entity.User;
import com.vu.service.UserService;
import com.vu.repository.RoleRepository;
import com.vu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;
	
	public UserServiceImpl(UserRepository userRepository,
							RoleRepository roleRepository,
							ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void addUser(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());
		List<Role> roleList = roleRepository.findRolesByUserId(
				Long.parseLong(PropertyConstant.ROLE_USER_ID_PROPERTY));
		Set<Role> roleSet = new HashSet<Role>(roleList);
		
		if (Optional.ofNullable(user).isEmpty()) {
			user = modelMapper.map(userDto, User.class);
			user.setRoles(roleSet);
			String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
			user.setPassword(hashedPassword);
			userRepository.save(user);
		}
	}

	@Override
	public void editUser(Long id, UserDto userDto) {
		if (Optional.ofNullable(userRepository.findById(id)).isPresent()) {
			User user = userRepository.findById(id).get();
			user = modelMapper.map(userDto, User.class);
			if (!userDto.getPassword().isEmpty()) {
				String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
				user.setPassword(hashedPassword);
			}
			user.setId(id);
			userRepository.save(user);
		}
	}

	@Override
	public void removeUser(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (Optional.ofNullable(user).isPresent()) {
			LOGGER.info(MessageConstant.DELETE_USER_BY_ID, userId);
			userRepository.deleteById(user.getId());
		}
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream()
						.map(user -> modelMapper.map(user, UserDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public List<UserDto> getUsersByFullName(String fullname) {
		String likeFullName = "%" + fullname + "%";
		List<User> users = userRepository.findByFullName(likeFullName);
		return users.stream()
						.map(user -> modelMapper.map(user, UserDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId).get();
		return modelMapper.map(user, UserDto.class);
	}

}
