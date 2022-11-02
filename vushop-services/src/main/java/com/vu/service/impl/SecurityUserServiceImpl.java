package com.vu.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vu.dto.UserDto;
import com.vu.entity.PasswordResetToken;
import com.vu.entity.User;
import com.vu.repository.PasswordResetTokenRepository;
import com.vu.service.SecurityUserService;

@Service
public class SecurityUserServiceImpl implements SecurityUserService{
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
		
		if (passwordResetToken == null || passwordResetToken.getUser().getId() != id) {
			 return "invalidToken";
		}
		
		Calendar calendar = Calendar.getInstance();
		if ((passwordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime()) < 0) {
			 return "expired";
		}
		
		User user = passwordResetToken.getUser();
		
	}

}
