package com.vu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vu.payload.request.LoginRequest;
import com.vu.payload.response.LoginResponse;
import com.vu.repository.RoleRepository;
import com.vu.repository.UserRepository;
import com.vu.security.JwtTokenProvider;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	 @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			// Gọi hàm authenticate để xác thực thông tin đăng nhập
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			// Gọi hàm tạo Token
			String token = tokenProvider.generateToken(authentication);
			return new ResponseEntity<LoginResponse>(new LoginResponse("Đăng nhập thành công!", token), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LoginResponse>(new LoginResponse("Đăng nhập thất bại!", null), HttpStatus.BAD_REQUEST);
		}
	}
}
