package com.liyi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liyi.dto.AdminLoginDto;
import com.liyi.entity.Admin;
import com.liyi.service.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/index")
	public String index(HttpSession httpSession){
		if(httpSession.getAttribute("admin")==null){
			return "redirect:/admin/login";	
		}
		return "admin/index";
		
	}
	
	@RequestMapping("/login")
	public String login(AdminLoginDto request,HttpSession httpSession){
		Admin admin=adminService.login(request);
		if(admin==null){
			return "admin/login";
		}else{
			httpSession.setAttribute("admin", admin);
			return "redirect:/admin/index";	
		}
	}
	
	@RequestMapping("/logout")
	public String adminLogin(HttpSession httpSession){
		
		httpSession.removeAttribute("admin");  //注销
		return "redirect:admin/index";
		
	}
}
