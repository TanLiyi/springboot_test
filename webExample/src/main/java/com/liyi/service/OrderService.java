package com.liyi.service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.liyi.dto.*;
import com.liyi.entity.*;
import com.liyi.exception.CommonCode;
import com.liyi.exception.ServiceException;
import com.liyi.repository.*;
import com.liyi.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRespostory;
	
	@Autowired
	private UserRepository userRespostory;
	
	@Autowired
	private CartSubRepository cartsubRespostory;
	
	@Autowired
	private LeaveConfigRespostory configRespostory;
	
	@Autowired
	private GoodRepository goodReprositry;

	@Autowired
	private UserAddressService addressService;

	@Autowired
	private OrderAddressRepository orderAddressReprositry;
	
	@Autowired
	private OrderSubRepository orderSubRespostory;
	
	
	public List<OrderDto> getOrderList(){
		List<Order> orders=orderRespostory.findAllOrder();
		List<OrderDto> list=orders.stream().map(e->{
			OrderDto orderDto=BeanMapper.map(e,OrderDto.class);
			List<OrderGoodsDto> goods=e.getOrderSub().stream().map(o->{
				OrderGoodsDto goodsDto=new OrderGoodsDto();
				goodsDto.setGoods(o.getGood());
				goodsDto.setBuyCount(o.getBuyCount());
				return goodsDto;
			}).collect(Collectors.toList());
			orderDto.setGoods(goods);
			orderDto.setAddress(e.getAddress());
			return  orderDto;
		}).collect(Collectors.toList());
		return list;
		
	}
	
	public List<OrderDto> getUserList(Integer userId){
		List<Order> orders=orderRespostory.findUserOrder(userId);
		List<OrderDto> list=orders.stream().map(e->{
			OrderDto orderDto=BeanMapper.map(e,OrderDto.class);
			List<OrderGoodsDto> goods=e.getOrderSub().stream().map(o->{
				OrderGoodsDto goodsDto=new OrderGoodsDto();
				goodsDto.setGoods(o.getGood());
				goodsDto.setBuyCount(o.getBuyCount());
				return goodsDto;
			}).collect(Collectors.toList());
			orderDto.setGoods(goods);
			orderDto.setAddress(e.getAddress());
			return  orderDto;
		}).collect(Collectors.toList());
		return list;
		
	}
	
	public OrderDto getDetail(Integer orderId){
		Order order=orderRespostory.findById(orderId);
		OrderDto orderDto=BeanMapper.map(order,OrderDto.class);
		List<OrderGoodsDto> goods=order.getOrderSub().stream().map(o->{
			OrderGoodsDto goodsDto=new OrderGoodsDto();
			goodsDto.setGoods(o.getGood());
			goodsDto.setBuyCount(o.getBuyCount());
			return goodsDto;
		}).collect(Collectors.toList());
		orderDto.setGoods(goods);
		orderDto.setAddress(order.getAddress());
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
			response.setFavourable(config.getLeaveName()+"会员共优惠"+new DecimalFormat("0.00").format(cut) +"元");
			
			Order order=new Order();
			order.setCreateTime(new Date());
			order.setDeleted(0);
			order.setUserId(userId);
			order.setOrderStatus(0);
			order.setOrderCode(LocalTime.now().toString());
			order.setRealPrice(realPay);
			order.setTotalPrice(total);
			order.setFavourable(config.getLeaveName()+"会员，共优惠"+new DecimalFormat("0.00").format(cut)+"元");
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
					if(g.getTotalStockQty()<0){
						throw new ServiceException(CommonCode.BAD_REQUEST,"库存不足");
					}
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
		response.setFavourable(config.getLeaveName()+"会员，共优惠"+new DecimalFormat("0.00").format(cut)+"元");
		
		Order order=new Order();
		order.setCreateTime(new Date());
		order.setDeleted(0);
		order.setUserId(userId);
		order.setOrderStatus(0);
		order.setOrderCode(LocalTime.now().toString());
		order.setRealPrice(realpri);
		order.setTotalPrice(total);
		order.setFavourable(config.getLeaveName()+"会员，共优惠"+new DecimalFormat("0.00").format(cut)+"元");
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
			if(g.getTotalStockQty()<0){
				throw new ServiceException(CommonCode.BAD_REQUEST,"库存不足");
			}
		}
		goodReprositry.save(g);
		return response;
	}
	@Transactional
	public void pay(PayDto request){
		Order order=orderRespostory.findById(request.getOrderId());
		order.setOrderStatus(1); //0-未 1-支付 2-待收 3-完成
		AddressDto addressDto=addressService.getDetail(request.getAddressId());
		OrderAddress orderAddress= BeanMapper.map(addressDto,OrderAddress.class);
		orderAddressReprositry.save(orderAddress);
		order.setAddressId(orderAddress.getId());
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
