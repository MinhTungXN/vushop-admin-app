package com.vu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vu.security.JwtTokenProvider;
import com.vu.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null 
        		|| AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
	}
	
	@Override
	public boolean isValidToken(String authToken) {
		String jwt = jwtTokenProvider.getJwtFromBearerToken(authToken);	
		if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(authToken)) {
			return true;
		}
		return false;
	}
}
