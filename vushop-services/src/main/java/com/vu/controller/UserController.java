package com.vu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vu.constant.MessageConstant;
import com.vu.constant.SecurityConstant;
import com.vu.constant.UrlConstant;
import com.vu.dto.UserDto;
import com.vu.payload.request.SearchRequest;
import com.vu.service.SecurityService;
import com.vu.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstant.USER_API_URL)
@Api(value = "vu-shop-v02", description = "Operations pertaining to users in vushopv02")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@ApiOperation(value = "View user list", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "The resource you were trying to reach is not exist")
	})
	@GetMapping({"/all"})
	public ResponseEntity<?> getAll(@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		List<UserDto> userDtos = userService.getUsers();
		if (userDtos.isEmpty()) {
			return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);	
	}

	@ApiOperation(value = "Get an user with an ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Long id,
									@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		UserDto userDto = userService.getUserById(id);
		if (userDto == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Search an user with a keyword")
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest,
									@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {		
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		List<UserDto> userDtos = null;
		if (searchRequest.getKeyword() != null && !searchRequest.getKeyword().isEmpty()) {
			userDtos = userService.getUsersByFullName(searchRequest.getKeyword());
			if (userDtos.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add a new user")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody UserDto userDto,
								 @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			userService.addUser(userDto);
			return new ResponseEntity<String>("Add successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") Long id, 
								  @RequestBody UserDto userDto,
								  @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			if (userService.getUserById(id) == null) {
				return new ResponseEntity<String>("Not found any user!", HttpStatus.BAD_REQUEST);
			}
			userService.editUser(id, userDto);
			return new ResponseEntity<String>("Edit successfully!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id,
			@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		userService.removeUser(id);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/reset_password/{id}")
	public ResponseEntity<?> resetPassword(@PathVariable("id") Long id, 
								  @RequestBody String password,
								  @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			if (userService.getUserById(id) == null) {
				return new ResponseEntity<String>("Not found any user!", HttpStatus.BAD_REQUEST);
			}
			userService.resetPassword(id, password);
			return new ResponseEntity<String>("reset password successfully!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}
