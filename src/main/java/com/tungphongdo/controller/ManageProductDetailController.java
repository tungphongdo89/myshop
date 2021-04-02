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

import com.tungphongdo.repository.ProductDetailRepository;

@Controller
@RequestMapping("/manageProductDetail")
public class ManageProductDetailController {
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	@GetMapping("/{page}/{size}")
	public String manageProductDetail(Model model, @PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = productDetailRepository.findProductDetails(pageable).getTotalPages();
		
		model.addAttribute("listProductDetails", productDetailRepository.findProductDetails(pageable));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageProductDetail";
		
	}
	
	@GetMapping("/search/{page}/{size}")
	public String searchProduct(Model model, @PathVariable int page, @PathVariable int size,
			@RequestParam("productName") String productName) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = productDetailRepository.findProductDetails(pageable).getTotalPages();
		
		model.addAttribute("listProductDetails", productDetailRepository.findProductsWithPartOfName(productName));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", "b");
		
		return "Admin/ManageProductDetail";
		
	}

}
