package com.liyi.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.OrderDto;
import com.liyi.dto.OrderGoodsDto;
import com.liyi.dto.OrderInputDto;
import com.liyi.dto.PayDto;
import com.liyi.dto.PaySureDto;
import com.liyi.entity.CartSub;
import com.liyi.entity.Goods;
import com.liyi.entity.LeaveConfig;
import com.liyi.entity.Order;
import com.liyi.entity.OrderSub;
import com.liyi.entity.User;
import com.liyi.repository.CartSubRespostory;
import com.liyi.repository.GoodRepository;
import com.liyi.repository.LeaveConfigRespostory;
import com.liyi.repository.OrderRespostory;
import com.liyi.repository.OrderSubRespostory;
import com.liyi.repository.UserReprositry;

@Service
public class OrderService {
	
	@Autowired
	private OrderRespostory orderRespostory;
	
	@Autowired
	private UserReprositry userRespostory;
	
	@Autowired
	private CartSubRespostory cartsubRespostory;
	
	@Autowired
	private LeaveConfigRespostory configRespostory;
	
	@Autowired
	private GoodRepository goodReprositry;
	
	@Autowired
	private OrderSubRespostory orderSubRespostory;
	
	public List<OrderDto> getOrderList(){
		List<OrderDto> list=new ArrayList<>();
		List<Order> orders=orderRespostory.findAllOrder();
		for(Order order:orders){
			OrderDto dto=this.getDetail(order.getId());
			list.add(dto);
		}
		return list;
		
	}
	
	public List<OrderDto> getUserList(Integer Id){
		List<OrderDto> list=new ArrayList<>();
		List<Order> orders=orderRespostory.findUserOrder(Id);
		for(Order order:orders){
			OrderDto dto=this.getDetail(order.getId());
			list.add(dto);
		}
		return list;
		
	}
	
	public OrderDto getDetail(Integer id){
		OrderDto orderDto=new OrderDto();
		Order order=orderRespostory.findById(id);
		if(order==null){
			return orderDto;
		}
		orderDto.setId(order.getId());
		orderDto.setCreateTime(order.getCreateTime());
		orderDto.setOrderCode(order.getOrderCode());
		orderDto.setStatus(order.getOrderStatus());
		orderDto.setUser(order.getUser());
		orderDto.setRealPice(order.getRealPrice());
		orderDto.setDesc(order.getFavourable());
		orderDto.setAddress(order.getAddress());
		orderDto.setTotal(order.getTotalPrice());
		List<OrderGoodsDto> goods=order.getOrderSub().stream().map(e->{
			OrderGoodsDto good=new OrderGoodsDto();
			good.setGoods(e.getGood());
			good.setBuyCount(e.getBuyCount());
			return good;
		}).collect(Collectors.toList());
		orderDto.setGoods(goods);
		return orderDto;
	}
	@Transactional
	public void delete(Integer id){
		Order order=orderRespostory.findById(id);
		order.setDeleted(1);
		orderRespostory.save(order);
	}
	
	//生成订单
	@Transactional
	public PaySureDto byCart(String[] ids,Integer userId){
		PaySureDto response = new PaySureDto();
		List<Integer> collect = Arrays.stream(ids).map(id -> new Integer(id)).collect(Collectors.toList());
		if (collect != null && !collect.isEmpty()) {
			double total = 0;
			double i=100;   //计算百分比用的  例如 优惠百分比为10  那么应该计算总价格*90%
			double realPay=0;
			List<Integer> subGoogIds=new ArrayList<>();
			List<CartSub> subs=cartsubRespostory.findIdIn(collect);
			for(CartSub sub : subs){
				Integer goodId=sub.getGoodId();
				subGoogIds.add(goodId);
				total+=sub.getPrice()*sub.getQty();  //应付金额
			}
			//实付金额
			User user=userRespostory.findById(userId);
			LeaveConfig config=configRespostory.findByPoint(user.getPoint());
			if(config.getBaifen()==0){
				realPay=total;
			}else{
				realPay=((i-config.getBaifen())/100)*total;
			}
			response.setTotal(total);
			response.setRealPrice(realPay);
			double cut=total-realPay;
			response.setFavourable(config.getLeaveName()+"会员共优惠"+cut+"元");
			
			Order order=new Order();
			order.setCreateTime(new Date());
			order.setDeleted(0);
			order.setUserId(userId);
			order.setOrderStatus(0);
			order.setOrderCode(LocalTime.now().toString());
			order.setRealPrice(realPay);
			order.setTotalPrice(total);
			order.setFavourable(config.getLeaveName()+"会员，共优惠"+cut+"元");
			orderRespostory.save(order);
			List<OrderInputDto> goods=new ArrayList<>();
			for(CartSub sub : subs){
				OrderInputDto good=new OrderInputDto();
				good.setBuyCount(sub.getQty());
				good.setGoodId(sub.getGoodId());
				String pic=goodReprositry.findbyId(sub.getGoodId()).getPic();
				good.setPic(pic);
				good.setGoodName(goodReprositry.findbyId(sub.getGoodId()).getName());
				good.setPrice(sub.getPrice()*sub.getQty());
				good.setSingle(sub.getPrice());
				goods.add(good);
				
				OrderSub su=new OrderSub();
				su.setOrderId(order.getId());
				su.setBuyCount(sub.getQty());
				su.setGoodId(sub.getGoodId());
				su.setCreateTime(new Date());
				su.setGoodPrice(sub.getPrice());
				orderSubRespostory.save(su);
				
				Goods g=goodReprositry.findbyId(sub.getGoodId());
				//购买成功后扣除库存
				if(g!=null&&g.getTotalSaleQty()!=null&&g.getTotalSaleQty()==0){  //第一次购买的时候
					g.setTotalSaleQty(sub.getQty());
					g.setTotalStockQty(g.getTotalStockQty()-sub.getQty());
					g.setUpdateTime(new Date());
				}else{
					g.setTotalSaleQty(g.getTotalSaleQty()+sub.getQty());
					g.setTotalStockQty(g.getTotalStockQty()-sub.getQty());
					g.setUpdateTime(new Date());
				}
				goodReprositry.save(g);
			}
			response.setGoods(goods);
			response.setOrderId(order.getId());
		}
		return response;
		
	}
	@Transactional
	public PaySureDto buyNow(OrderInputDto request,Integer userId){
		PaySureDto response=new PaySureDto();
		double i=100;   //计算百分比用的  例如 优惠百分比为10  那么应该计算总价格*90%
		double total=request.getBuyCount()*request.getPrice();
		response.setTotal(total);
		User user = userRespostory.findById(userId);
		LeaveConfig config=configRespostory.findByPoint(user.getPoint());
		double realpri=((i-config.getBaifen())/100)*total;
		response.setRealPrice(realpri);
		double cut=total-realpri;
		response.setFavourable(config.getLeaveName()+"会员，共优惠"+cut+"元");
		
		Order order=new Order();
		order.setCreateTime(new Date());
		order.setDeleted(0);
		order.setUserId(userId);
		order.setOrderStatus(0);
		order.setOrderCode(LocalTime.now().toString());
		order.setRealPrice(realpri);
		order.setTotalPrice(total);
		order.setFavourable(config.getLeaveName()+"会员，共优惠"+cut+"元");
		orderRespostory.save(order);
		
		List<OrderInputDto> goods=new ArrayList<>();
		OrderInputDto good=new OrderInputDto();
		good.setGoodId(request.getGoodId());
		good.setPic(request.getPic());
		good.setGoodName(request.getGoodName());
		good.setSingle(request.getPrice());
		good.setPrice(request.getPrice()*request.getBuyCount());
		good.setBuyCount(request.getBuyCount());
		goods.add(good);
		response.setGoods(goods);
		
		OrderSub sub=new OrderSub();
		sub.setOrderId(order.getId());
		sub.setBuyCount(request.getBuyCount());
		sub.setGoodId(request.getGoodId());
		sub.setCreateTime(new Date());
		sub.setGoodPrice(request.getPrice()*request.getBuyCount());
		orderSubRespostory.save(sub);
		response.setOrderId(order.getId());
		Goods g=goodReprositry.findbyId(request.getGoodId());
		//购买成功后扣除库存
		if(g!=null&&g.getTotalSaleQty()!=null&&g.getTotalSaleQty()==0){  //第一次购买的时候
			g.setTotalSaleQty(request.getBuyCount());
			g.setTotalStockQty(g.getTotalStockQty()-request.getBuyCount());
			g.setUpdateTime(new Date());
		}else{
			g.setTotalSaleQty(g.getTotalSaleQty()+request.getBuyCount());
			g.setTotalStockQty(g.getTotalStockQty()-request.getBuyCount());
			g.setUpdateTime(new Date());
		}
		goodReprositry.save(g);
		return response;
	}
	@Transactional
	public void pay(PayDto request){
		Order order=orderRespostory.findById(request.getOrderId());
		if(order==null){
			
		}
		order.setOrderStatus(1); //0-未 1-支付 2-待收 3-完成
		order.setAddressId(request.getAddressId());
		orderRespostory.save(order);
	}
	@Transactional
	public void sig(Integer id){
		Order order=orderRespostory.findById(id);
		if(order!=null&&order.getOrderStatus()==2){
			order.setOrderStatus(3); //0-未 1-支付 2-待收 3-完成
			orderRespostory.save(order);
		}
	}
	@Transactional
	public void fahuo(Integer id){
		Order order=orderRespostory.findById(id);
		if(order!=null&&order.getOrderStatus()==1){
			order.setOrderStatus(2); //0-未 1-支付 2-待收 3-完成
			orderRespostory.save(order);
		}
	}
}
