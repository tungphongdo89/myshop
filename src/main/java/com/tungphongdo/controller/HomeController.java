package com.tungphongdo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.CategoryEntity;
import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.entity.UserRoleEntity;
import com.tungphongdo.repository.CategoryRepository;
import com.tungphongdo.repository.ProductDetailBillRepository;
import com.tungphongdo.repository.ProductDetailRepository;

@Controller
@RequestMapping(value = {"/","/home"})
public class HomeController {
//	@GetMapping
//	public String home( Model model) {
//		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
//			return "Home/Home";
//		}
//		else {
//			CustomUserDetail users = 
//					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				
//				String firstChar = (String) users.getUsername().subSequence(0, 1);
//				
//				model.addAttribute("UserInfor", firstChar.toUpperCase());
//				return "Home/Home";
//		}
//		
//		
//	}
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductDetailBillRepository productDetailBillRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	@GetMapping
	public String doHome(Model model) {
		
		List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
		categoryEntities = categoryRepository.findAll();
		
		Pageable pageable = PageRequest.of(0, 4);
		
		
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
		CustomUserDetail users = 
				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			
			Set<UserRoleEntity> userRoleEntities =  users.getUserEntity().getUserRoleEntities();
			
			for (UserRoleEntity userRoleEntity : userRoleEntities) {
				if(userRoleEntity.getRoleEntity().getRoleName().contains("ADMIN")) {
					model.addAttribute("roleAdmin", userRoleEntity.getRoleEntity().getRoleName());
				}
			}
		
			String firstChar = (String) users.getUsername().subSequence(0, 1);
			
			model.addAttribute("UserInfor", firstChar.toUpperCase());
			model.addAttribute("listCategories", categoryEntities);
			model.addAttribute("limitProductDetails", productDetailBillRepository.findLimitProductDetailBySort(pageable));
			model.addAttribute("limitProducts", productDetailRepository.findLimitProductBySort(pageable));
			return "Home/Home";
		}
		else {
			model.addAttribute("listCategories", categoryEntities);
			model.addAttribute("limitProductDetails", productDetailBillRepository.findLimitProductDetailBySort(pageable));
			model.addAttribute("limitProducts", productDetailRepository.findLimitProductBySort(pageable));
			return "Home/Home";
		}
		
		
	}

}
