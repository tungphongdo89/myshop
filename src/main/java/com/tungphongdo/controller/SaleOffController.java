package com.tungphongdo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.SaleOffDetailEntity;
import com.tungphongdo.repository.SaleOffDetailRepository;

@Controller
@RequestMapping("/saleoff")
public class SaleOffController {
	@Autowired
	private SaleOffDetailRepository saleOffDetailRepository;
	
	@GetMapping
	public String saleOff(Model model) {
		List<SaleOffDetailEntity> saleOffDetailEntities = saleOffDetailRepository.findAll();
		model.addAttribute("listSaleOffDetais", saleOffDetailEntities);
		return "Product/SaleOff";
	}

}
