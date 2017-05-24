package com.liyi.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CommonResponseDto;
import com.liyi.dto.UserInfoDto;
import com.liyi.dto.UserInfoInputDto;
import com.liyi.entity.User;
import com.liyi.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService ;
	
	@RequestMapping("/persion")
	public String getinfo(ModelMap model,@RequestParam("id") Integer id){
		UserInfoDto info=userService.getUserInfo(id);
		model.put("info",info);
		return "user/persion";
		
	}
	
	@RequestMapping("/update")
	public String update(UserInfoInputDto request,ModelMap model,HttpSession session){
		CommonResponseDto responst=userService.updateUser(request);
		User user=(User) session.getAttribute("user");
		UserInfoDto info=userService.getUserInfo(user.getId());
		model.put("info",info);
		model.put("message",responst.getMessage());
		return "user/persion";
		
	}
	
	@RequestMapping("/registered")
	public ModelAndView reg(ModelMap model,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if(request.getParameter("username")==null||request.getParameter("username").isEmpty()){
			modelAndView.setViewName("user/registered");
			return modelAndView;
		}
		UserInfoInputDto input = new UserInfoInputDto();
		input.setName(request.getParameter("username"));
		input.setPassword(request.getParameter("password"));
		
		input.setSex(Integer.valueOf(request.getParameter("sex")));
		input.setTel(request.getParameter("tel"));
		if (input.getName() == null && input.getSex() == null && input.getPassword() == null
				&& input.getTel() == null) {
			modelAndView.setViewName("user/registered");
		} else {
			CommonCreateResponseDto response = userService.createUser(input);
			if (response != null) {
				model.put("message", response.getMessage()+"请登录");
				if (response.getId() != null) {
					modelAndView.setViewName("login");
				} else {
					modelAndView.setViewName("user/registered");
				}
			}
		}
		return modelAndView;
	}
	
	@RequestMapping("/detial/{id}")
	public ModelAndView info(ModelMap model,@PathVariable("id") Integer id){
		ModelAndView view=new ModelAndView();
		UserInfoDto user=userService.getUserInfo(id);
		model.put("user",user );
		view.setViewName("user/userInfo");
		return view;
	}
	@RequestMapping("/del/{id}")
	public String del(@PathVariable("id") Integer id){
		userService.deleted(id);
		return "index";
	}
	
	@RequestMapping("/detial/update")
	public ModelAndView update(ModelMap model,UserInfoInputDto update){
		ModelAndView view=new ModelAndView();
		CommonResponseDto user=userService.updateUser(update);
		model.put("user",user );
		view.setViewName("user/userInfo");
		return view;
	}
	
	@RequestMapping("/sigup")
	public String signIn(@RequestParam("id") Integer id){
		userService.signup(id);
		return "redirect:persion?id="+id;
	}
}

