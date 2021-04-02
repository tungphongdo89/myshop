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

import com.tungphongdo.repository.ColorRepository;
import com.tungphongdo.service.ColorService;

@Controller
@RequestMapping("/manageColor")
public class ManageColorController {
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private ColorService colorService;
	
	@GetMapping("/{page}/{size}")
	public String manageSize(Model model, @PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = colorRepository.findColor(pageable).getTotalPages();
		
		model.addAttribute("listColors", colorRepository.findColor(pageable));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageColor";
	}
	
	@GetMapping("/search/{page}/{size}")
	public String searchSize(Model model, @PathVariable int page, @PathVariable int size,
			@RequestParam("colorName") String colorName) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = colorRepository.findColor(pageable).getTotalPages();
		
		model.addAttribute("listColors", colorService.findByColorName(colorName));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "b");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageColor";
	}

}
