package com.vu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vu.dto.RoleDto;
import com.vu.entity.Role;
import com.vu.repository.RoleRepository;
import com.vu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<RoleDto> getAll() {
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		try {
			List<Role> entities = roleRepository.findAll();
			for (Role role: entities) {
				dtos.add(new RoleDto(
						role.getId(), 
						role.getName(),
						role.getDesc()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public RoleDto getById(Long id) {
		Role entity = roleRepository.findById(id).get();
		return new RoleDto(
				entity.getId(), 
				entity.getName(),
				entity.getDesc());
	}
	
	public void save(RoleDto dto) {
		Role entity = new Role();
		entity.setName(dto.getName());
		entity.setDesc(dto.getDescription());
		
		roleRepository.save(entity);
	}

	public void edit(RoleDto dto) {
		Role entity = roleRepository.findById(dto.getId()).get();
		if(entity != null) {
			entity.setName(dto.getName());
			entity.setDesc(dto.getDescription());
			
			roleRepository.save(entity);
		}
	}

	public void remove(Long id) {
		Role entity = roleRepository.findById(id).get();
		if(entity != null) {
			roleRepository.deleteById(id);
		}
	}
}
