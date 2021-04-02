package com.tungphongdo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.CategoryEntity;
import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.entity.ProductDetailEntity;
import com.tungphongdo.entity.ProductEntity;
import com.tungphongdo.repository.CategoryRepository;
import com.tungphongdo.repository.ProductDetailRepository;
import com.tungphongdo.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	
	@GetMapping("/{id}")
	public String product(Model model, @PathVariable int id) {
		List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
		categoryEntities = categoryRepository.findAll();
		
		CategoryEntity categoryEntity = categoryRepository.findById(id).get();
		
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				String firstChar = (String) users.getUsername().subSequence(0, 1);
				
				model.addAttribute("UserInfor", firstChar.toUpperCase());
				model.addAttribute("category", categoryEntity);
				model.addAttribute("listCategories", categoryEntities);
				return "Product/Product";
			}
			else {
				model.addAttribute("category", categoryEntity);
				model.addAttribute("listCategories", categoryEntities);
				return "Product/Product";
			}
	}
	
	//Detail page
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable int id) {
		ProductEntity productEntity = productRepository.findById(id).get();
		
		List<ProductDetailEntity> listProductDetailEntities = productDetailRepository.findByProductId(id);
		
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String firstChar = (String) users.getUsername().subSequence(0, 1);
			model.addAttribute("UserInfor", firstChar.toUpperCase());
			
		
			model.addAttribute("listDetail", listProductDetailEntities);
			model.addAttribute("product", productEntity);
				
				return "Product/Detail";
		}
		else {
			
			model.addAttribute("listDetail", listProductDetailEntities);
			model.addAttribute("product", productEntity);
			return "Product/Detail";
		}
		
		
	}

}
