package com.liyi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.AddCartInputDto;
import com.liyi.dto.CartResponseDto;
import com.liyi.dto.UpdateCartRequestDto;
import com.liyi.entity.User;
import com.liyi.service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/")
	public String cart(HttpSession httpSession, ModelMap model) {
		User users = (User) httpSession.getAttribute("user");
		CartResponseDto response = cartService.get(users.getId());
		model.put("cart", response);
		return "/cart";
	}
	@RequestMapping("/list")
	public String cartList(HttpSession httpSession, ModelMap model) {
		User users = (User) httpSession.getAttribute("user");
		CartResponseDto response = cartService.get(users.getId());
		model.put("cart", response);
		return "/cart";
	}
	@RequestMapping("/del")
	public String detete(HttpSession httpSession, ModelMap model, @RequestParam("id") Integer id) {
		cartService.delete(id);
		return "redirect:list";
	}
	@RequestMapping("delMuti")
    public String delMore(@RequestParam("ids")String ids){
		cartService.deleteMuti(ids.split(","));
		return "redirect:list";
	}
	@RequestMapping("update")
	public String update(UpdateCartRequestDto request, HttpSession httpSession, ModelMap model) {
		cartService.updateCart(request);
		return "redirect:list";
	}

	// 分类列表过来的
	@RequestMapping("product")
	public String add(AddCartInputDto request, HttpSession httpSession, @RequestParam("id") Integer id) {
		User users = (User) httpSession.getAttribute("user");
		request.setUserId(users.getId());
		cartService.add(request);
		return "redirect:/products?gid=" + id;

	}

	// 首页过来的
	@RequestMapping("index")
	public String indexAdd(ModelMap model, AddCartInputDto request, HttpSession httpSession) {
		User users = (User) httpSession.getAttribute("user");
		request.setUserId(users.getId());
		cartService.add(request);
		model.put("message", "加入成功");
		return "redirect:/index";
	}

	// 详情页过来的
	@RequestMapping("single")
	public String singleAdd(ModelMap model, AddCartInputDto request, HttpSession httpSession) {
		User users = (User) httpSession.getAttribute("user");
		request.setUserId(users.getId());
		cartService.add(request);
		model.put("message", "加入成功");
		return "redirect:/detail?id=" + request.getGoodId();
	}

}
