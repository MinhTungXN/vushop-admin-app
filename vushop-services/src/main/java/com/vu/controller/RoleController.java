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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vu.constant.MessageConstant;
import com.vu.constant.SecurityConstant;
import com.vu.dto.RoleDto;
import com.vu.service.RoleService;
import com.vu.service.SecurityService;

@RestController
@RequestMapping("api/role")
@CrossOrigin("*")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SecurityService securityService;

	@GetMapping("/all")
	public ResponseEntity<?> getAll(@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			List<RoleDto> roles = roleService.getAll();
			return new ResponseEntity<Object>(roles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody RoleDto roleDto,
								 @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			roleService.save(roleDto);
			return new ResponseEntity<Object>("Add successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getById(@RequestParam Long id,
									 @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			RoleDto role = roleService.getById(id);
			return new ResponseEntity<Object>(role, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable(name = "id") Long id,
								 @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			RoleDto role = roleService.getById(id);
			return new ResponseEntity<Object>(role, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@PathVariable(name = "id") Long id, 
								  @RequestBody RoleDto roleDto,
								  @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			if (roleService.getById(id) == null) {
				return new ResponseEntity<Object>("Id Not Found!", HttpStatus.BAD_REQUEST);
			}
			roleService.edit(roleDto);
			return new ResponseEntity<Object>("Updated Sucsessfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id,
									@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			roleService.remove(id);
			return new ResponseEntity<Object>("Delete Sucsessfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
