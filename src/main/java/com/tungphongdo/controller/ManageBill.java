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

import com.tungphongdo.repository.BillRepository;

@Controller
@RequestMapping("/manageBill")
public class ManageBill {
	@Autowired
	private BillRepository billRepository;
	
	@GetMapping("/{page}/{size}")
	public String manageBill(Model model, @PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = billRepository.findBills(pageable).getTotalPages();
		
		model.addAttribute("listBills", billRepository.findBills(pageable));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageBill";
	}
	
	@GetMapping("/search/{page}/{size}")
	public String searchBill(Model model, @PathVariable int page, @PathVariable int size, 
			@RequestParam("date") String date) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = billRepository.findBills(pageable).getTotalPages();
		
		model.addAttribute("listBills", billRepository.findBillsWithDate(date));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "b");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageBill";
	}

}
