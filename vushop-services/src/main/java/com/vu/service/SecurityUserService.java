package com.vu.service;

public interface SecurityUserService {
	
	String validatePasswordResetToken(long id, String token);

}
