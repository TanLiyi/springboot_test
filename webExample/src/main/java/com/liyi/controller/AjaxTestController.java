package com.liyi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyi.dto.LoginDto;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/test")
public class AjaxTestController {


	@RequestMapping("/")
	public String test() {

		return "/test";

	}

	@RequestMapping(value="test")
	@ResponseBody
	public ResponseEntity<Object> login(ModelMap model, LoginDto userLogin, HttpSession httpSession) {
		JSONObject jsonObject = new JSONObject();
		if (userLogin.getPassword() == null && userLogin.getUsername() == null) {
			jsonObject.put("message", "用名或密码为空！");
		} else {
				jsonObject.put("message", "登录成功");
		}
		return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
	}
}
