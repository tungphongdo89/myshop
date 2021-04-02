package com.tungphongdo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.CustomUserDetail;

@Controller
@RequestMapping("/contact")
public class ContactController {
	
//	@GetMapping
//	public String contact() {
//		return "Home/Contact";
//	}
	
	@GetMapping
	public String contact( Model model) {
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
		CustomUserDetail users = 
				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String firstChar = (String) users.getUsername().subSequence(0, 1);
			
			model.addAttribute("UserInfor", firstChar.toUpperCase());
			return "Home/Contact";
		}
		else {
			return "Home/Contact";
		}
		
		
	}

}
