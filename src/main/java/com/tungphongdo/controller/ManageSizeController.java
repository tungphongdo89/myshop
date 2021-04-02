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

import com.tungphongdo.repository.SizeRepository;
import com.tungphongdo.service.SizeService;

@Controller
@RequestMapping("/manageSize")
public class ManageSizeController {
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private SizeService sizeService;
	
	@GetMapping("/{page}/{size}")
	public String manageSize(Model model, @PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = sizeRepository.findSize(pageable).getTotalPages();
		
		model.addAttribute("listSizes", sizeRepository.findSize(pageable));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageSize";
	}
	
	@GetMapping("/search/{page}/{size}")
	public String searchSize(Model model, @PathVariable int page, @PathVariable int size,
			@RequestParam("sizeName") String sizeName) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = sizeRepository.findSize(pageable).getTotalPages();
		
		model.addAttribute("listSizes", sizeService.findBySizeName(sizeName));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "b");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageSize";
	}

}
