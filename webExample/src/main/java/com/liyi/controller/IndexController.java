package com.liyi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liyi.dto.GoodDetailDto;
import com.liyi.dto.IndexRequestDto;
import com.liyi.dto.LoginDto;
import com.liyi.entity.Category;
import com.liyi.entity.Goods;
import com.liyi.entity.User;
import com.liyi.service.CategoryService;
import com.liyi.service.GoodsService;
import com.liyi.service.StatService;
import com.liyi.service.UserService;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StatService statService;
	
	@RequestMapping("/index")
	public ModelAndView index(ModelMap model,IndexRequestDto request,HttpSession session) throws Exception {
		ModelAndView modelAndView=new ModelAndView();
		List<Category> categorys=categoryService.get();
		model.put("categorys", categorys);
		session.setAttribute("categorys", categorys);
		List<Goods> list=goodsService.getIndexGoods();    //首页获取的top6商品
		model.put("list", list);
		if(request.getName()!=null){  //模糊查询商品
			list=goodsService.getIndexGoods();
			modelAndView.setViewName("products");
		}else{
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	
	@RequestMapping("/products")
	public String index(ModelMap model,HttpSession session,@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int page,@RequestParam(value = "id",defaultValue="") Integer id){
		session.setAttribute("categoryId", id);
		model.put("page", goodsService.getGoods(search,page,id));
		model.put("search", search);
		model.put("categoryId", id);
		model.put("currentPage", goodsService.getGoods(search,page,id).getCurrentPage());
		List<Category> categorys=categoryService.get();
		model.put("categorys", categorys);
		return "products";
	}
	
	@RequestMapping("/productPage")
	public String page(ModelMap model,HttpSession session,@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int page){
		Integer id=(Integer) session.getAttribute("categoryId");
		model.put("page", goodsService.getGoods(search,page,id));
		model.put("search", search);
		model.put("categoryId", id);
		model.put("currentPage", goodsService.getGoods(search,page,id).getCurrentPage());
		List<Category> categorys=categoryService.get();
		model.put("categorys", categorys);
		return "products";
	}
	
	@RequestMapping("/login")
	public String login(ModelMap model,HttpServletRequest request,HttpSession httpSession){
		List<Category> categorys=categoryService.get();
		model.put("categorys", categorys);
		LoginDto userLogin = new LoginDto();
		userLogin.setUsername(request.getParameter("username"));
		userLogin.setPassword(request.getParameter("password"));
		User user=new User();
		
		if(userLogin.getPassword()==null&&userLogin.getUsername()==null){
			return "/login";
		}else{
				user = userService.login(userLogin);
				
			if(user!=null){
				httpSession.setAttribute("user", user);
				model.put("user", user);
				model.put("message", "登录成功");
				return "redirect:index";
			}else{
				model.put("message", "用名或密码错误，请重新登录！");
				return "/login";
			}
		}
	}
	
	@RequestMapping("loginout")
	public String logout(HttpSession httpSession){
		httpSession.removeAttribute("user");  //注销
		return "redirect:index";
	}
	
	@RequestMapping("detail")
	public String getDetail(ModelMap model, HttpServletRequest request,@RequestParam("id") Integer id) {
		List<Category> categorys=categoryService.get();
		model.put("categorys", categorys);
		if(request.getParameter("id")!=null){
			GoodDetailDto detail = goodsService.getDetail(Integer.valueOf(request.getParameter("id")));
			statService.statGoodvisit(id);
			model.put("detail", detail.getDetail());
		}
		return "/detail";
	}
	
}
