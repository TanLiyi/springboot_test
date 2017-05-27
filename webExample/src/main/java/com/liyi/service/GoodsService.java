package com.liyi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.liyi.LiyiContants;
import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CreateGoodInput;
import com.liyi.dto.GoodDetailDto;
import com.liyi.dto.IndexRequestDto;
import com.liyi.entity.Goods;
import com.liyi.repository.GoodRepository;
import com.liyi.utils.CustomSpecications;
import com.liyi.utils.OffsetRequest;
import com.liyi.utils.PageableBean;
import com.liyi.utils.SearchFilter;
import com.liyi.utils.SearchFilter.Operator;

@Service
public class GoodsService {

	@Autowired
	private GoodRepository goodRepository;

	public List<Goods> getGoods(IndexRequestDto request) {
		List<Goods> list = new ArrayList<>();
		if (request.getCategoryId() != null) {
			list = goodRepository.findOnShalf(request.getCategoryId());
		}else
		if(!request.getName().isEmpty()){
			list=goodRepository.finByName(request.getName());
		}else{
			List<Goods> goods=goodRepository.findIndexTopSaleGood();
			int size = goods.size() > 6 ? 6 : goods.size();
			for (int i = 0; i < size; i++) {
				list.add(list.get(i));
			}
		}
		return list;
	}

	public PageableBean<Goods> getList(String name,int page){
		List<SearchFilter> filters=Lists.newArrayList();
		filters.add(new SearchFilter("name",name,Operator.LIKE));
		filters.add(new SearchFilter("isOnShelf",1));
		Specification<Goods> spec=CustomSpecications.searchFilterSpec(filters, Goods.class);
		Page<Goods> findAll=goodRepository.findAll(spec,
				new OffsetRequest(Math.max(0, page)*LiyiContants.PAGE_SIZE,LiyiContants.PAGE_SIZE));
		return new PageableBean<>(page,LiyiContants.PAGE_SIZE,findAll.getContent(),(int)findAll.getTotalElements());
	}
	
	public PageableBean<Goods> getGoods(String name,int page,Integer categoryId){
		List<SearchFilter> filters=Lists.newArrayList();
		filters.add(new SearchFilter("name",name,Operator.LIKE));
		filters.add(new SearchFilter("categoryId",categoryId));
		filters.add(new SearchFilter("isOnShelf",1));
		Specification<Goods> spec=CustomSpecications.searchFilterSpec(filters, Goods.class);
		Page<Goods> findAll=goodRepository.findAll(spec,
				new OffsetRequest(Math.max(0, page)*30,30));
		return new PageableBean<>(page,30,findAll.getContent(),(int)findAll.getTotalElements());
	}
	
	@Transactional
	public CommonCreateResponseDto createOrUpdate(CreateGoodInput request){
		CommonCreateResponseDto response=new CommonCreateResponseDto();
		Goods good=new Goods();
		if(request.getId()!=null){
			good=goodRepository.findbyId(request.getId());
			if(good!=null){
				good.setName(request.getName());
				good.setPic(request.getPic());
				good.setSalePrice(request.getSalePrice());
				good.setDesc(request.getDesc());
				good.setTotalStockQty(request.getTotalStockQty());
				good.setCategoryId(request.getCategoryId());
				response.setMessage("商品编辑成功！");
			}
		}else{
			good=new Goods();
			good.setName(request.getName());
			good.setPic(request.getPic());
			good.setDesc(request.getDesc());
			good.setSalePrice(request.getSalePrice());
			good.setTotalStockQty(request.getTotalStockQty());
			good.setCategoryId(request.getCategoryId());
			response.setMessage("商品添加成功！");
		}
		goodRepository.save(good);
		
		return response;
	}
	
	
	public GoodDetailDto getDetail(Integer id){
		GoodDetailDto response=new GoodDetailDto();
		Goods good=goodRepository.findbyId(id);
		if(good==null){
			response.setMessage("该商品信息不存在");
		}else{
			CreateGoodInput goodDto=new CreateGoodInput();
			goodDto.setId(good.getId());
			goodDto.setCount(good.getCount());
			goodDto.setCategoryId(good.getCategoryId());
			goodDto.setDesc(good.getDesc());
			goodDto.setName(good.getName());
			goodDto.setPic(good.getPic());
			goodDto.setCategoryName(good.getCategory().getName());
			goodDto.setSalePrice(good.getSalePrice());
			goodDto.setTotalStockQty(good.getTotalStockQty());
			response.setMessage("已找到商品信息");
			response.setDetail(goodDto);
		}
		return response;
	}
	@Transactional
	public void delete(Integer id){
		goodRepository.delete(id);
	}
	
	public List<Goods> getIndexGoods(){
		List<Goods> list = goodRepository.findIndexTopSaleGood();
		
		return list;
	}
}
