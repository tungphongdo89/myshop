package com.tungphongdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tungphongdo.entity.SizeEntity;
import com.tungphongdo.repository.SizeRepository;

@Service
public class SizeService {
	@Autowired
	private SizeRepository sizeRepository;
	
	public List<SizeEntity> findBySizeName(String sizeName){
		return sizeRepository.findBySizeNameLike("%"+sizeName+"%");
	}
	
}
