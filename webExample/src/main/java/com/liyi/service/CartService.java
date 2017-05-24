package com.liyi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.AddCartInputDto;
import com.liyi.dto.CartDto;
import com.liyi.dto.CartResponseDto;
import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CommonResponseDto;
import com.liyi.dto.UpdateCartRequestDto;
import com.liyi.entity.Cart;
import com.liyi.entity.CartSub;
import com.liyi.entity.Goods;
import com.liyi.repository.CartReprositry;
import com.liyi.repository.CartSubRespostory;
import com.liyi.repository.GoodRepository;

@Service
public class CartService {

	@Autowired
	private CartReprositry cartReprositry;
	
	@Autowired
	private CartSubRespostory cartSubRespostory;
	
	@Autowired
	private GoodRepository goodRepository;
	
	@Transactional
	public CartResponseDto get(Integer userId){
		Cart cart=this.isExist(userId);
		List<CartDto> cartDtos=new ArrayList<>();
		double totalPrice = 0;
		if(cart.getCartSub()!=null){
			for(CartSub sub:cart.getCartSub()){
				if(sub.getDeleted()==0){
					CartDto cartDto=new CartDto();
					cartDto.setId(sub.getId());
					cartDto.setGoodId(sub.getGoodId());
					cartDto.setCount(sub.getQty());
					
					Goods good=goodRepository.findbyId(sub.getGoodId());
					cartDto.setGoodName(good.getName());
					cartDto.setPic(good.getPic());
					cartDto.setPrice(good.getSalePrice());
					cartDto.setTotalprice(sub.getPrice());   //单个明细的总价格
					cartDtos.add(cartDto);
					//计算购物车中的总金额
					totalPrice+=cartDto.getTotalprice()*cartDto.getCount();
				}
				
			}
		}
		CartResponseDto response=new CartResponseDto();
		response.setCartDto(cartDtos);
		response.setTotal(cart.getCartSub().size());
		response.setTotalPrice(totalPrice);
		return response;
		
	}
	@Transactional
	public void deleteMuti(String[] ids) {
		List<Integer> collect = Arrays.stream(ids).map(id -> new Integer(id)).collect(Collectors.toList());
		if (collect != null && !collect.isEmpty()) {
			this.cartSubRespostory.deleteIdIn(collect);
		}
	}
	@Transactional
	public CommonResponseDto updateCart(UpdateCartRequestDto request){
		CommonResponseDto response=new CommonResponseDto();
		CartSub cartSub=cartSubRespostory.findById(request.getId());
		if(cartSub ==null){
			response.setMessage("该商品信息不存在！");
			return response;
		}
		cartSub.setQty(request.getCount());
		cartSubRespostory.save(cartSub);
		response.setMessage("修改商品成功！");
		return response;
	}
	
	@Transactional
	public CommonResponseDto delete(Integer cartSubId){
		CommonResponseDto response=new CommonResponseDto();
		cartSubRespostory.delete(cartSubId);
		response.setMessage("将商品从购物车中移除成功！");
		return response;
		
	}
	
	@Transactional
	public CommonCreateResponseDto add(AddCartInputDto request){
		CommonCreateResponseDto response=new CommonCreateResponseDto();
		//购物车最多只能添加10条记录
		Cart cart = isExist(request.getUserId());
//		if(cart.getCartSub()!=null&&cart.getCartSub().size()>10){
//			response.setMessage("购物车最多只能添加10条商品信息！");
//			return response;
//		}
		if(cart.getCartSub()!=null){
			CartSub sub=cartSubRespostory.findByIdAndGoodId(cart.getId(),request.getGoodId());
			if(sub!=null){
				sub.setQty(sub.getQty()+request.getBuyCount());
			}else{
				CartSub cartSub=new CartSub();
				cartSub.setCartId(cart.getId());
				cartSub.setGoodId(request.getGoodId());
				cartSub.setQty(request.getBuyCount());
				cartSub.setPrice(request.getPrice()*request.getBuyCount());
				cartSubRespostory.save(cartSub);
			}
		}else{
			CartSub cartSub=new CartSub();
			cartSub.setCartId(cart.getId());
			cartSub.setGoodId(request.getGoodId());
			cartSub.setQty(request.getBuyCount());
			cartSub.setPrice(request.getPrice()*request.getBuyCount());
			cartSubRespostory.save(cartSub);
		}
		return response;
	}
	
	private Cart isExist(Integer userId){
		Cart cart=cartReprositry.findByUserId(userId);
		if(cart==null){
			cart=new Cart();
			cart.setUserId(userId);
			cartReprositry.save(cart);
		}
		return cart;
	}
	
}
