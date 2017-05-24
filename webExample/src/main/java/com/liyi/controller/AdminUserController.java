package com.liyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.UserInfoDto;
import com.liyi.dto.UserInfoInputDto;
import com.liyi.service.UserService;

@Controller
@RequestMapping("/admin/member")
public class AdminUserController {

	@Autowired
	private UserService userService ;
	
	@RequestMapping("/index")
	public String index(ModelMap model, @RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		model.put("page", userService.getList(search, page));
		model.put("currentPage", userService.getList(search, page).getCurrentPage());
		model.put("search", search);
		return "/admin/member/index";
	}
	@RequestMapping("/detail")
	public String info(ModelMap model,@RequestParam("id") Integer id){
		UserInfoDto user=userService.getUserInfo(id);
		model.put("user",user );
		return "/admin/member/detail";
	}
	@RequestMapping("/del")
	public String del(@RequestParam("id") Integer id){
		userService.deleted(id);
		return "redirect:index";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap model,UserInfoInputDto update){
		userService.updateUser(update);
		return "redirect:index";
	}
	
	@RequestMapping("delMuti")
    public String delMore(@RequestParam("ids")String ids){
		userService.deleteMuti(ids.split(","));
		return "redirect:index";
	}
}
