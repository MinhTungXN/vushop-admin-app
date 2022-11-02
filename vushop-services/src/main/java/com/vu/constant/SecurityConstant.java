package com.vu.constant;

public class SecurityConstant {

	// --- Spring Security Attributes
    public static final String REQUIRED_LOGIN_ANY_ROLES = "hasAnyRole('ROLE_USER', 'ROLE_ADMIN')";
    public static final String REQUIRED_LOGIN_ADMIN_ROLE = "hasRole('ROLE_ADMIN')";
    public static final String AUTH_TOKEN_HEADER = "Authorization";
}
