package com.liyi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.AddressDto;
import com.liyi.dto.AddressEditDto;
import com.liyi.dto.CommonCreateResponseDto;
import com.liyi.dto.CommonResponseDto;
import com.liyi.entity.Address;
import com.liyi.repository.AddressReprositry;

@Service
public class UserAddressService {

	@Autowired
	private AddressReprositry addressReprositry;
	
	public List<AddressDto> getAddressList(Integer uid){
		 List<Address> address = addressReprositry.findByUserId(uid);
		List<AddressDto> addressDtos=new ArrayList<>();
		if(address!=null&&address.size()>0){
			addressDtos = address.stream().map(e -> {
				AddressDto addressDto = new AddressDto();
				addressDto.setUserId(uid);
				addressDto.setId(e.getId());
				addressDto.setCiyt(e.getCiyt());
				addressDto.setIsDefault(e.getIsDefault());
				addressDto.setNickName(e.getConsignor());
				addressDto.setPri(e.getPri());
				addressDto.setStreet(e.getStreet());
				addressDto.setTel(e.getTel());
				addressDto.setTown(e.getTown());
				return addressDto;
			}).collect(Collectors.toList());
		}
		return addressDtos;
	}
	
/*	public AddressDto getDefaule(Integer userId){
		AddressDto dto=new AddressDto();
		Address address=addressReprositry.findDefault(userId);
		dto.setCiyt(address.getCiyt());
		dto.setId(address.getId());
		dto.setNickName(address.getConsignor());
		dto.setPri(address.getPri());
		dto.setStreet(address.getStreet());
		dto.setTel(address.getTel());
		dto.setTown(address.getTown());
		return dto;
		
	}*/
	
	public AddressDto getDetail(Integer id){
		AddressDto dto=new AddressDto();
		Address address=addressReprositry.findById(id);
		dto.setCiyt(address.getCiyt());
		dto.setId(address.getId());
		dto.setNickName(address.getConsignor());
		dto.setPri(address.getPri());
		dto.setStreet(address.getStreet());
		dto.setTel(address.getTel());
		dto.setTown(address.getTown());
		return dto;
		
	}
	@Transactional
	public CommonCreateResponseDto save(AddressEditDto request){
		CommonCreateResponseDto response=new CommonCreateResponseDto();
		List<Address> list=addressReprositry.findByUserId(request.getUserId());
		if(list!=null&&list.size()>5){   //避免浪费数据库资源 只允许添加5条地址
			response.setMessage("亲，您的收件地址已经超出最大限制，可以先适当的进行删改哦~");
			return response;
		}
		Address address=new Address();
		if(list==null||list.size()==0){ //说明是第一个地址 为默认地址
			address.setCiyt(request.getCiyt());
			address.setConsignor(request.getNickName());
			address.setCreateTime(new Date());
			address.setDeleted((byte)0);
			address.setPri(request.getPri());
			address.setStreet(request.getStreet());
			address.setTel(request.getTel());
			address.setTown(request.getTown());
			address.setUserId(request.getUserId());
			address.setIsDefault(1);
			response.setMessage("添加成功");
		}else{
			address.setCiyt(request.getCiyt());
			address.setUserId(request.getUserId());
			address.setConsignor(request.getNickName());
			address.setCreateTime(new Date());
			address.setDeleted((byte)0);
			address.setPri(request.getPri());
			address.setStreet(request.getStreet());
			address.setTel(request.getTel());
			address.setTown(request.getTown());
			address.setIsDefault(0);
			response.setMessage("添加成功");
		}
		addressReprositry.save(address);
		return response;
	}
	
	@Transactional
	public CommonResponseDto update(AddressEditDto request){
		CommonResponseDto response=new CommonResponseDto();
		Address address=addressReprositry.findById(request.getId());
		if(address==null){
			response.setMessage("地址不存在");
			return response;
		}
		address.setCiyt(request.getCiyt());
		address.setConsignor(request.getNickName());
		address.setDeleted((byte)0);
		address.setPri(request.getPri());
		address.setStreet(request.getStreet());
		address.setTel(request.getTel());
		address.setTown(request.getTown());
		address.setUpdateTime(new Date());
		addressReprositry.save(address);
		response.setMessage("编辑成功");
		return response;
	}
	
	@Transactional
	public CommonResponseDto delete(Integer id){
		CommonResponseDto response=new CommonResponseDto();
		Address address=addressReprositry.findById(id);
		if(address==null){
			response.setMessage("地址不存在");
			return response;
		}
		address.setDeleted((byte)1);
		addressReprositry.save(address);
		response.setMessage("删除成功");
		return response;
		
	}
	
	@Transactional
	public CommonResponseDto setDefault(Integer id,Integer uid){
		CommonResponseDto response=new CommonResponseDto();
		Address address1=addressReprositry.findByUserIdAndIsDefault(uid);
		if(address1!=null){
			address1.setIsDefault(0);
			addressReprositry.save(address1);
		}
		Address address=addressReprositry.findById(id);
		if(address==null){
			response.setMessage("地址不存在");
			return response;
		}
		address.setIsDefault(1);
		addressReprositry.save(address);
		
		return response;
	}
}
