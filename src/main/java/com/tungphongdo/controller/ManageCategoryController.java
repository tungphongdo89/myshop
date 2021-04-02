package com.tungphongdo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tungphongdo.repository.CategoryRepository;
import com.tungphongdo.service.CategoryService;

@Controller
@RequestMapping("/manageCategory")
public class ManageCategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{page}/{size}")
	public String manageCategory(Model model, @PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = categoryRepository.findCategory(pageable).getTotalPages();
		
		model.addAttribute("listCategory", categoryRepository.findCategory(pageable));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageCategory";
	}
	
	@GetMapping("/search/{page}/{size}")
	public String searchCategory(Model model, @PathVariable int page, @PathVariable int size,
			@RequestParam("categoryName") String categoryName) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = categoryRepository.findCategory(pageable).getTotalPages();
		
		model.addAttribute("listCategory", categoryService.findByCategoryName(categoryName));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "b");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageCategory";
	}

}
