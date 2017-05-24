package com.liyi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.OrderDto;
import com.liyi.service.OrderService;

@Controller
@RequestMapping("admin/order")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		List<OrderDto> list=orderService.getOrderList();
		model.put("list", list);
		return "admin/order/index";
	}
	
	@RequestMapping("")
	public String detail(@RequestParam("id")Integer id,ModelMap model){
		OrderDto list=orderService.getDetail(id);
		model.put("list", list);
		return "admin/order/detail";
	}
	@RequestMapping("fahuo")
	public String fahuo(@RequestParam("id")Integer id,ModelMap model){
		orderService.fahuo(id);;
		return "redirect:list";
	}
	
}
