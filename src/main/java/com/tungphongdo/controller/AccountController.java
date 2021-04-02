package com.tungphongdo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.repository.UsersRepository;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	@GetMapping
	public String account(Model model) {
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				String firstChar = (String) users.getUsername().subSequence(0, 1);
				
				UserEntity userEntity = usersRepository.findByUsername(users.getUsername());
				
				
				model.addAttribute("UserInfor", firstChar.toUpperCase());
				model.addAttribute("userEntity", userEntity);
				return "Login/Account";
			}
			else {
				return "Login/Account";
			}
		
		
	}

}
