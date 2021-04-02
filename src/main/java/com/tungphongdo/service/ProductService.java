package com.tungphongdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tungphongdo.entity.ProductEntity;
import com.tungphongdo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductEntity> findByProductName(String productName){
		return productRepository.findByProductNameLike("%"+productName+"%");
	}

}
