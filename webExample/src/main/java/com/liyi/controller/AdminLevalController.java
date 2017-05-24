package com.liyi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.LeaveConfigDto;
import com.liyi.entity.LeaveConfig;
import com.liyi.service.LeaveConfigService;

@Controller
@RequestMapping("admin/level")
public class AdminLevalController {

	@Autowired
	private LeaveConfigService configService;
	
	@RequestMapping("list")
	public String getList(ModelMap model){
		List<LeaveConfig> list=configService.getList();
		model.put("list", list);
		return "admin/level/index";
	}
	
	@RequestMapping("detail")
	public String get(ModelMap model,@RequestParam("id")Integer id){
		LeaveConfigDto detail=configService.get(id);
		model.put("detail", detail);
		return "admin/level/detail";
	}
	
	@RequestMapping("del")
	public String det(ModelMap model,@RequestParam("id")Integer id){
		configService.deleted(id);;
		return "redirect:list";
	}
	
	@RequestMapping("delMuti")
	public String delMuti(ModelMap model,@RequestParam("ids")Integer ids){
		configService.deleted(ids);;
		return "redirect:list";
	}
	
	@RequestMapping("create")
	public String create(ModelMap model,LeaveConfigDto request){
		if(request==null||request.getLeaveName()==null){
			return "admin/level/detail";
		}
		CommonCreateResponseDto response=configService.create(request);
		if(response!=null&&response.getId()!=null){
			return "redirect:list";
		}else{
			model.put("message", response.getMessage());
			return "admin/level/detail";
		}
	}
	@RequestMapping("update")
	public String update(ModelMap model,LeaveConfigDto request){
		configService.update(request);;
			return "redirect:list";
	}
	
}
