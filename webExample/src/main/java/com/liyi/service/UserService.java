package com.liyi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.liyi.LiyiContants;
import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CommonResponseDto;
import com.liyi.dto.LoginDto;
import com.liyi.dto.UserInfoDto;
import com.liyi.dto.UserInfoInputDto;
import com.liyi.entity.Cart;
import com.liyi.entity.LeaveConfig;
import com.liyi.entity.Points;
import com.liyi.entity.User;
import com.liyi.repository.CartReprositry;
import com.liyi.repository.LeaveConfigRespostory;
import com.liyi.repository.PointsReprositry;
import com.liyi.repository.UserReprositry;
import com.liyi.utils.BeanMapper;
import com.liyi.utils.CustomSpecications;
import com.liyi.utils.DateUtils;
import com.liyi.utils.OffsetRequest;
import com.liyi.utils.PageableBean;
import com.liyi.utils.SearchFilter;
import com.liyi.utils.SearchFilter.Operator;

@Service
public class UserService {

	@Autowired
	private UserReprositry userReprositry;

	@Autowired
	private PointsReprositry pointsReprositry;

	@Autowired
	private CartReprositry cartReprositry;
	
	@Autowired
	private LeaveConfigRespostory  leaveConfigRespostory;

	public PageableBean<User> getList(String username, int page) {
		List<SearchFilter> filters = Lists.newArrayList();
		filters.add(new SearchFilter("deleted", 0));
		filters.add(new SearchFilter("username", username, Operator.LIKE));
		Specification<User> spec = CustomSpecications.searchFilterSpec(filters, User.class);

		Page<User> findAll = userReprositry.findAll(spec,
				new OffsetRequest(Math.max(0, page) * LiyiContants.PAGE_SIZE, LiyiContants.PAGE_SIZE));

		return new PageableBean<>(page, LiyiContants.PAGE_SIZE, findAll.getContent(), (int) findAll.getTotalElements());
	}

	@Transactional
	public CommonCreateResponseDto createUser(UserInfoInputDto inputDto) {
		CommonCreateResponseDto response=new CommonCreateResponseDto();
		
		User user = new User();
		if (!inputDto.getName().isEmpty()) {
			user = userReprositry.findByName(inputDto.getName());
			if (user != null) {
				response.setMessage("该用户名已经存在！");
				return response;
			}
		}
		if(!inputDto.getTel().isEmpty()){
			user=userReprositry.findByTel(inputDto.getTel());
			if (user != null) {
				response.setMessage("该电话号码已经被注册过！");
				return response;
			}
		}
		user = new User();
		user.setUsername(inputDto.getName());
		user.setPassword(inputDto.getPassword());
		user.setSex(inputDto.getSex());
		user.setPoint(10);
		user.setTel(inputDto.getTel());
		user.setCreateTime(new Date());
		userReprositry.save(user);

		Points point = new Points();
		point.setUserId(user.getId());
		point.setPoint(10);
		LeaveConfig config=leaveConfigRespostory.findByPoint(point.getPoint());
		if(config!=null){
			user.setLeaveName(config.getLeaveName());
		}
		point.setPointDesc("用户注册");
		point.setCreateTime(new Date());
		pointsReprositry.save(point);
		
		Cart cart = new Cart();
		cart.setUserId(user.getId());
		cartReprositry.save(cart);
		if (user.getId() == null) {
			response.setMessage("注册失败");
		} else {
			response.setId(user.getId());
			response.setMessage("注册成功！");
		}
		return response;
	}

	@Transactional
	public CommonResponseDto updateUser(UserInfoInputDto inputDto) {
		CommonResponseDto response=new CommonResponseDto();
		User user = userReprositry.findById(inputDto.getId());
		if (!inputDto.getName().isEmpty()) {
			User user1 = userReprositry.findByName(inputDto.getName());
			if(user1==null){
				User user2 = userReprositry.findByName(inputDto.getName());
				if (user2 != null) {
					response.setMessage("该用户名已经存在！");
					return response;
				}
			}
		}
		if(!inputDto.getTel().isEmpty()){
			User user2=userReprositry.findUserTel(inputDto.getTel(), inputDto.getId());
			if(user2==null){
				User user3=userReprositry.findByTel(inputDto.getTel());
				if (user3 != null) {
					response.setMessage("该电话号码已经被注册过！");
					return response;
				}
			}
		}
		user.setUsername(inputDto.getName());
		user.setTel(inputDto.getTel());
		user.setSex(inputDto.getSex());
		user.setUpdateTime(new Date());
		userReprositry.save(user);
		response.setMessage("修改成功");
		return response;
	}

	@Transactional
	public void deleted(Integer id) {
		User user = userReprositry.findById(id);
		if (user != null) {
			user.setDeleted(1);
			user.setUpdateTime(new Date());
		}
	}

	public UserInfoDto getUserInfo(Integer id) {
		User user = userReprositry.findById(id);
		List<LeaveConfig> leaveConfigs=leaveConfigRespostory.findAllLeaveConfig(); 
		//因为等级积分可以改动，并不能单靠id去匹配，因此采用积分去匹配才保险
		for(LeaveConfig config:leaveConfigs){
			if(user.getPoint()<=config.getMinPoint()){
				user.setLeaveName(config.getLeaveName());;
				userReprositry.save(user);
			}
		}
		User user1 = userReprositry.findById(id);
		Points point = pointsReprositry.findByUserId(user.getId()).get(0);  //获取签到状态
		
		UserInfoDto info = BeanMapper.map(user1, UserInfoDto.class);
		info.setStatus(point.getStatus());
		return info;
	}

	public User login(LoginDto request) {
		User user = userReprositry.findByNameAndPassword(request.getUsername(), request.getPassword());
		return user;
	}

	@Transactional
	public void deleteMuti(String[] ids) {
		List<Integer> collect = Arrays.stream(ids).map(id -> new Integer(id)).collect(Collectors.toList());
		if (collect != null && !collect.isEmpty()) {
			this.userReprositry.deleteIdIn(collect);
		}
	}
	
	@Transactional
	public CommonResponseDto signup(Integer userId) {
		CommonResponseDto response=new CommonResponseDto();
		User user = userReprositry.findById(userId);
		if (user == null) {
			response.setMessage("用户不存在");
			return response;
		} else {
			Points point = pointsReprositry.findByUserId(user.getId()).get(0);  //如果今天没有签到的话，找到的是最近一次的记录
			if(point!=null){  
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTime = formatter.format(LocalDateTime.now());
				String pointTime = DateUtils.dateToLocalDateTime(point.getCreateTime());
				if(dateTime.equals(pointTime)&&point.getStatus()==1){    //说明今天已经签到过了
					response.setMessage("今日已经签到过来了，请明天再来哦~");
				}else{   //最近一次签到  分两种情况
					Points	point1=new Points();
					if(point.getStatus()==1){   //连续签到
						point1.setUserId(user.getId());
						point1.setPoint(5);
						point1.setStatus(1);
						user.setPoint(point.getPoint()+5);
						point1.setCreateTime(new Date());
						point1.setPointDesc("每日签到");
						response.setMessage("连续签到成功，加5积分");
					}else{
						point1.setUserId(user.getId());
						point1.setPoint(3);
						point1.setStatus(1);
						user.setPoint(point.getPoint()+3);
						point1.setCreateTime(new Date());
						point1.setPointDesc("每日签到");
						response.setMessage("今日签到成功，加3积分");
					}
					pointsReprositry.save(point1);
					userReprositry.save(user);
					//签到完之后，给这个用户增加一条第二天的未签到记录
					point=new Points();
					point.setStatus(0);
					point.setUserId(user.getId());
					point.setCreateTime(DateUtils.getNextDate(new Date()));
					pointsReprositry.save(point);
				}
			}
			return response;
		}
	}

}
