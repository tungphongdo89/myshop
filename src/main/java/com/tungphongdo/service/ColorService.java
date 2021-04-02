package com.tungphongdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tungphongdo.entity.ColorEntity;
import com.tungphongdo.repository.ColorRepository;

@Service
public class ColorService {
	@Autowired
	private ColorRepository colorRepository;
	
	public List<ColorEntity> findByColorName(String colorName){
		return colorRepository.findByColorNameLike("%"+colorName+"%");
	}

}
