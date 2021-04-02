package com.tungphongdo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.AboutEntity;
import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.repository.AboutRepository;

@Controller
@RequestMapping("/about")
public class AboutController {
	@Autowired
	private AboutRepository aboutRepository;
	
	@GetMapping
	public String about( Model model) {
		
		List<AboutEntity> aboutEntities = aboutRepository.findAll();
		
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
		CustomUserDetail users = 
				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String firstChar = (String) users.getUsername().subSequence(0, 1);
			
			model.addAttribute("UserInfor", firstChar.toUpperCase());
			model.addAttribute("listAbout", aboutEntities);
			return "Home/About";
		}
		else {
			model.addAttribute("listAbout", aboutEntities);
			return "Home/About";
		}
		
		
	}

}
