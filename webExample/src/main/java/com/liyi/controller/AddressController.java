package com.liyi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.AddressDto;
import com.liyi.dto.AddressEditDto;
import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CommonResponseDto;
import com.liyi.entity.User;
import com.liyi.service.UserAddressService;

@Controller
@RequestMapping("address")
public class AddressController {

	@Autowired
	private UserAddressService addressService;

	@RequestMapping("/")
	public String getAddress(@RequestParam("id") Integer id, ModelMap model) {
		List<AddressDto> list = addressService.getAddressList(id);
		model.put("list", list);
		model.put("add", null);
		return "user/address";
	}

	@RequestMapping("updateDrup")
	public String tiao(@RequestParam("id") Integer id, ModelMap model, HttpSession session) {
		AddressDto address = addressService.getDetail(id);
		model.put("detail", address);
		model.put("id", id);
		User user = (User) session.getAttribute("user");
		List<AddressDto> list = addressService.getAddressList(user.getId());
		model.put("list", list);
		model.put("add", "update");
		return "user/address";
	}

	@RequestMapping("update")
	public String updateAddress(ModelMap model, AddressEditDto request, HttpSession session) {
		if (request != null && request.getNickName() != null) {
			CommonResponseDto response = addressService.update(request);
			model.put("message", response.getMessage());
		}
		User user = (User) session.getAttribute("user");
		List<AddressDto> list = addressService.getAddressList(user.getId());
		model.put("list", list);
		model.put("add", null);
		return "user/address";
	}

	@RequestMapping("/save")
	public String saveAddress(ModelMap model, AddressEditDto request) {
		CommonCreateResponseDto response = addressService.save(request);
		List<AddressDto> list = addressService.getAddressList(request.getUserId());
		model.put("list", list);
		model.put("message", response.getMessage());
		return "user/address";
	}

	@RequestMapping("/del")
	public String saveAddress(ModelMap model, @RequestParam("id") Integer id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		addressService.delete(id);
		return "redirect:/address/?id=" + user.getId();
	}

	@RequestMapping("/default")
	public String setDefault(ModelMap model, @RequestParam("id") Integer id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		addressService.setDefault(id, user.getId());
		return "redirect:/address/?id=" + user.getId();
	}
}
