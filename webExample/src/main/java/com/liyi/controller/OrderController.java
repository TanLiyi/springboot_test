package com.liyi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.AddressDto;
import com.liyi.dto.OrderDto;
import com.liyi.dto.OrderInputDto;
import com.liyi.dto.PayDto;
import com.liyi.dto.PaySureDto;
import com.liyi.entity.User;
import com.liyi.service.OrderService;
import com.liyi.service.UserAddressService;

@Controller
public class OrderController {
	
	@Autowired
	private UserAddressService addressService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("bycart")    //从购物车中购买  此处是购物车明细id
	public String paySure(HttpSession session,@RequestParam("ids") String ids,ModelMap model){
		User user=(User) session.getAttribute("user");
		List<AddressDto> list=addressService.getAddressList(user.getId());
//		AddressDto de=addressService.getDefaule(user.getId());
		model.put("list", list);
		PaySureDto paySure=orderService.byCart(ids.split(","),user.getId());
		model.put("pay", paySure);
		model.put("sureId", null);
		session.setAttribute("pay", paySure);
		session.setAttribute("list", list);
		return "/pay";
	}
	
	@RequestMapping("byDetail")    //从详情页购买  
	public String paySure1(HttpSession session,OrderInputDto request,ModelMap model){
		User user=(User) session.getAttribute("user");
		List<AddressDto> list=addressService.getAddressList(user.getId());
		model.put("list", list);
		PaySureDto paySure=orderService.buyNow(request, user.getId());
		model.put("pay", paySure);
		model.put("sureId", null);
		session.setAttribute("pay", paySure);
		session.setAttribute("list", list);
		return "/pay";
	}
	
	@RequestMapping("sureAddress") 
	public String sure(@RequestParam("id") Integer id,HttpSession session,ModelMap model){
		PaySureDto pay=(PaySureDto) session.getAttribute("pay");
		@SuppressWarnings("unchecked")
		List<AddressDto> list=(List<AddressDto>) session.getAttribute("list");
		model.put("list", list);
		model.put("pay", pay);
		model.put("sureId", id);
		return "/pay";
	}
	
	@RequestMapping("surepay")   //待确认支付
	public String surepay(@RequestParam("id") Integer id,HttpSession session,ModelMap model){
		User user=(User) session.getAttribute("user");
		List<AddressDto> list=addressService.getAddressList(user.getId());
		model.put("list", list);
		OrderDto order=orderService.getDetail(id);
		model.put("order", order);
		model.put("sureId", null);
		return "/pay";
	}
	
	@RequestMapping("order/detail")
	public String getDetail(@RequestParam("id") Integer id,ModelMap model){
		OrderDto order=orderService.getDetail(id);
		model.put("order", order);
		return "/user/orderdetail";
		
	}
	@RequestMapping("orders")
	public String getUsers(ModelMap model,HttpSession session){
		User user=(User) session.getAttribute("user");

		List<OrderDto> list=orderService.getUserList(user.getId());
		model.put("list", list);
		return "/user/ordermanege";
	}
	@RequestMapping("order/del")
	public String delted(@RequestParam("id") Integer id){
		orderService.delete(id);
		return "redirect:/orders";
	}
	@RequestMapping("pay")
	public String pay(PayDto request){
		orderService.pay(request);
		return "redirect:/index";
	}
	@RequestMapping("sig")
	public String sig(ModelMap model,@RequestParam("id") Integer id){
		orderService.sig(id);;
		OrderDto order=orderService.getDetail(id);
		model.put("order", order);
		return "/user/orderdetail";
	}
	
}
