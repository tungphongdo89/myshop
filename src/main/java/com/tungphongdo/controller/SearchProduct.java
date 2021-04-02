package com.tungphongdo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tungphongdo.entity.CategoryEntity;
import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.repository.CategoryRepository;
import com.tungphongdo.service.ProductService;

@Controller
@RequestMapping("/searchProduct")
public class SearchProduct {
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String searchProduct(Model model, @RequestParam("productName") String productName) {
		
		List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
		categoryEntities = categoryRepository.findAll();
		
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				String firstChar = (String) users.getUsername().subSequence(0, 1);
				
				model.addAttribute("UserInfor", firstChar.toUpperCase());
				model.addAttribute("listCategories", categoryEntities);
				model.addAttribute("listProducts", productService.findByProductName(productName));
				return "Product/SearchProduct";
			}
			else {
				model.addAttribute("listProducts", productService.findByProductName(productName));
				model.addAttribute("listCategories", categoryEntities);
				return "Product/SearchProduct";
			}
		
	}

}
