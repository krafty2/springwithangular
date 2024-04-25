package com.spring.angular.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.angular.models.Role;
import com.spring.angular.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}
	
	public Optional<Role> searchRole(Long id){
		return roleRepository.findById(id);
	}
	
	public Optional<Role> searchByRoleName(String name){
		return roleRepository.findByRoleName(name);
	}
}
