package com.liyi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.EditCategoryRequestDto;
import com.liyi.entity.Category;
import com.liyi.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/create")
	public String create(EditCategoryRequestDto request,ModelMap model,HttpSession httpSession){
		if(request.getName()==null||request.getName().isEmpty()){
			return "admin/category/detail";
		}else{
			CommonCreateResponseDto response=categoryService.create(request);
			model.put("message", response.getMessage());
			if(response.getId()==null){
				return "admin/category/detail";
			}else{
				return "redirect:index";
			}
		}
	}
	
	@RequestMapping("/index")
	public String index(ModelMap model,@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int page){
		model.put("page", categoryService.getList(search,page));
		model.put("search", search);
		model.put("currentPage", categoryService.getList(search,page).getCurrentPage());
		return "admin/category/index";
		
	}
	
	@RequestMapping("/detail")
	public String getDetail(@RequestParam("id") Integer id, ModelMap model, HttpSession httpSession) {
		Category category = categoryService.getDetail(id);
		model.put("category", category);
		return "admin/category/detail";

	}
	
	@RequestMapping("del")
    public String del(@RequestParam("id")Integer id){
		categoryService.deleted(id);
		return "redirect:index";
	}
	
	@RequestMapping("delMuti")
    public String delMore(@RequestParam("ids")String ids){
		categoryService.deleteMuti(ids.split(","));
		return "redirect:index";
	}
	
	@RequestMapping("/update")
	public String update(EditCategoryRequestDto request, ModelMap model, HttpSession httpSession) {
		categoryService.update(request);
		return "redirect:index";
	}
}
