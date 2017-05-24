package com.liyi.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CreateGoodInput;
import com.liyi.dto.GoodDetailDto;
import com.liyi.dto.GoodDetailRequestDto;
import com.liyi.entity.Category;
import com.liyi.service.CategoryService;
import com.liyi.service.GoodsService;

@Controller
@RequestMapping("/good")
public class GoodController {

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping("/create")
	public ModelAndView create(ModelMap model,CreateGoodInput good,@RequestParam(value="file",required=false) 
	MultipartFile file,HttpServletRequest request)throws Exception{
		ModelAndView view=new ModelAndView();
		List<Category> list=categoryService.get();
		//当id为空的时候，便是新增页面
			// 获得物理路径webapp所在路径
			String path = "";
			if (good.getId()!=null&&!good.getName().isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = file.getContentType();
				// 获得文件后缀名称
				String imageName = contentType.substring(contentType.indexOf("/") + 1);
				path = uuid + "." + imageName;
				/* 一下这里是把图片放在指定的文件中进行缓存读取 */
				File newFile = new File("D:/Documents/chapter5/src/main/resources/static/tan/images", path);
				newFile.getParentFile().mkdirs();
				file.transferTo(newFile);
				good.setPic(path);
				
				CommonCreateResponseDto response = goodsService.createOrUpdate(good);
				if (response == null) {
					model.put("categorys", list);
					view.setViewName("good/create");
				} else {
					view.setViewName("index");
				}
			}else{
				model.put("categorys", list);
				view.setViewName("good/create");
			}
			
		return view;
	}
	
	@RequestMapping("/detail")
	public ModelAndView adminGetDetail(ModelMap model,GoodDetailRequestDto request){
		ModelAndView view=new ModelAndView();
		
		List<Category> list=categoryService.get();
		if(request.getId()!=null){
			GoodDetailDto detail=goodsService.getDetail(request.getId());
			model.put("message", detail.getMessage());
			model.put("detail", detail.getDetail());
		}
		model.put("categorys", list);
		view.setViewName("good/create");
		return view;
	}

}
