package com.tungphongdo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.AboutEntity;
import com.tungphongdo.repository.AboutRepository;

@Controller
@RequestMapping("/manageAbout")
public class ManageAboutController {
	@Autowired
	private AboutRepository aboutRepository;
	
	@GetMapping
	public String manageAbout(Model model) {
		
		List<AboutEntity> aboutEntities = aboutRepository.findAll();
		model.addAttribute("listAbout", aboutEntities);
		return "Admin/ManageAbout";
	}

}
