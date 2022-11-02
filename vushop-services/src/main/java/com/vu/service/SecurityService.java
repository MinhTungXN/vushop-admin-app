package com.vu.service;

public interface SecurityService {

	boolean isAuthenticated();
	
	boolean isValidToken(String token);
}
