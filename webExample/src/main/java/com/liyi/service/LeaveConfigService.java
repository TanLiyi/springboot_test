package com.liyi.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.LeaveConfigDto;
import com.liyi.entity.LeaveConfig;
import com.liyi.repository.LeaveConfigRespostory;

@Service
public class LeaveConfigService {

	@Autowired
	private LeaveConfigRespostory leaveConfigRespostory;

	public List<LeaveConfig> getList() {
		List<LeaveConfig> list = leaveConfigRespostory.findAllLeaveConfig();
		return list;
	}

	@Transactional
	public CommonCreateResponseDto create(LeaveConfigDto request) {
		CommonCreateResponseDto response=new CommonCreateResponseDto();
		List<LeaveConfig> list=leaveConfigRespostory.findAllLeaveConfig();
		if(list!=null&&list.size()>4){
			response.setMessage("最多只能设置4个等级");
		}
		LeaveConfig config = new LeaveConfig();
		config.setLeaveName(request.getLeaveName());
		config.setMinPoint(request.getMinPoint());
		config.setBaifen(request.getBaifen());
		leaveConfigRespostory.save(config);
		response.setId(config.getId());
		return response;

	}
	
	public LeaveConfigDto get(Integer id){
		LeaveConfigDto config=new LeaveConfigDto();
		LeaveConfig request = leaveConfigRespostory.findOne(id);
		config.setId(id);
		config.setLeaveName(request.getLeaveName());
		config.setMinPoint(request.getMinPoint());
		config.setBaifen(request.getBaifen());
		return config;
		
	}
	@Transactional
	public void  update(LeaveConfigDto request) {
		LeaveConfig config = leaveConfigRespostory.findOne(request.getId());
		config.setLeaveName(request.getLeaveName());
		config.setMinPoint(request.getMinPoint());
		config.setBaifen(request.getBaifen());
		leaveConfigRespostory.save(config);
	}

	@Transactional
	public void deleted(Integer id){
		leaveConfigRespostory.delete(id);
	}
	
	@Transactional
	public void deleteMuti(String[] ids) {
		List<Integer> collect = Arrays.stream(ids).map(id -> new Integer(id)).collect(Collectors.toList());
		if (collect != null && !collect.isEmpty()) {
			for(Integer id:collect ){
				this.deleted(id);
			}
		}
	}
}
