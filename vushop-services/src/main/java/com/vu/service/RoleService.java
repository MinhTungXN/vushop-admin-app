package com.vu.service;

import java.util.List;

import com.vu.dto.RoleDto;

public interface RoleService {

	void save(RoleDto dto);
	
	List<RoleDto> getAll();
	
	RoleDto getById(Long id);
	
	void edit(RoleDto dto);

	void remove(Long id);
}
