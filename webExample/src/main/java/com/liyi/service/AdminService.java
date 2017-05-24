package com.liyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.AdminLoginDto;
import com.liyi.entity.Admin;
import com.liyi.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public Admin login(AdminLoginDto request){
		Admin admin=adminRepository.findByNameAndPassword(request.getName(), request.getPassword());
		return admin;
	}
}
