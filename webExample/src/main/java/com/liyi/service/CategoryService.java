package com.liyi.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.liyi.LiyiContants;
import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CommonResponseDto;
import com.liyi.dto.EditCategoryRequestDto;
import com.liyi.entity.Category;
import com.liyi.repository.CategoryRepository;
import com.liyi.utils.CustomSpecications;
import com.liyi.utils.OffsetRequest;
import com.liyi.utils.PageableBean;
import com.liyi.utils.SearchFilter;
import com.liyi.utils.SearchFilter.Operator;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository  categoryReprositry;
	
	public List<Category> get(){
		List<Category> list=categoryReprositry.findByDeleted(0);
		return list;
	}
	
	@Transactional
	public CommonCreateResponseDto create(EditCategoryRequestDto request){
		CommonCreateResponseDto response=new CommonCreateResponseDto();
		Category category=new Category();
		category.setName(request.getName());
		category.setDesc(request.getDesc());
		category.setCreateTime(new Date());
		categoryReprositry.save(category);
		if(category.getId()!=null){
			response.setId(category.getId());
			response.setMessage("添加分类成功");
		}else{
			response.setMessage("添加分类失败");
		}
		return response;
	}
	
	public PageableBean<Category> getList(String name,int page){
		List<SearchFilter> filters=Lists.newArrayList();
		filters.add(new SearchFilter("name",name,Operator.LIKE));
		filters.add(new SearchFilter("deleted",0));
		Specification<Category> spec=CustomSpecications.searchFilterSpec(filters, Category.class);
		Page<Category> findAll=categoryReprositry.findAll(spec,
				new OffsetRequest(Math.max(0, page)*LiyiContants.PAGE_SIZE,LiyiContants.PAGE_SIZE));
		return new PageableBean<>(page,LiyiContants.PAGE_SIZE,findAll.getContent(),(int)findAll.getTotalElements());
		
	}
	
	@Transactional
	public CommonResponseDto update(EditCategoryRequestDto request){
		CommonResponseDto response=new CommonResponseDto();
		Category category=categoryReprositry.findById(request.getId());
		if(category==null){
			response.setMessage("找不到分类"+request.getId());
			return response;
		}
		category.setName(request.getName());
		category.setDesc(request.getDesc());
		category.setUpdateTime(new Date());
		categoryReprositry.save(category);
		response.setMessage("修改成功");
		return response;
	}
	
	public Category getDetail(Integer id){
		return categoryReprositry.findById(id);
	}
	
	@Transactional
	public void deleted(Integer id){
		Category category=categoryReprositry.findById(id);
		category.setDeleted(1);
		category.setUpdateTime(new Date());
	}
	@Transactional
	public void deleteMuti(String[] ids) {
		List<Integer> collect = Arrays.stream(ids).map(id -> new Integer(id)).collect(Collectors.toList());
		if (collect != null && !collect.isEmpty()) {
			this.categoryReprositry.deleteIdIn(collect);
		}
	}
}
