package com.vu.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vu.constant.AttributeConstant;
import com.vu.constant.MessageConstant;
import com.vu.constant.ParameterConstant;
import com.vu.entity.User;
import com.vu.exception.ResourceNotFoundException;
import com.vu.repository.UserRepository;
import com.vu.security.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
			() -> new UsernameNotFoundException(MessageConstant.USER_NOT_FOUND_MESSAGE + usernameOrEmail)
        );
		return UserPrincipal.create(user);
	}
	
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException(AttributeConstant.USER_ATTRIBUTE, ParameterConstant.ID_PARAMETER, id)
		);	
		return UserPrincipal.create(user);
	}

}
