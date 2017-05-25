package com.liyi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyi.dto.LoginDto;
import com.liyi.entity.User;
import com.liyi.service.UserService;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/test")
public class AjaxTestController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String test() {

		return "/test";

	}

	@RequestMapping(value="test",consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> login(ModelMap model, LoginDto userLogin, HttpSession httpSession) {
		JSONObject jsonObject = new JSONObject();
		if (userLogin.getPassword() == null && userLogin.getUsername() == null) {
			jsonObject.put("message", "用名或密码为空！");
		} else {
			User user = userService.login(userLogin);
			if (user != null) {
				jsonObject.put("user", user);
				jsonObject.put("message", "登录成功");
			} else {
				jsonObject.put("message", "用名或密码错误，请重新登录！");
			}
		}
		return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
	}
}
