package com.tungphongdo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.repository.UsersRepository;

@Controller
@RequestMapping("/cart")
@SessionAttributes("carts")
public class CartController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	@GetMapping
	public String cart(Model model, HttpSession session) {
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String firstChar = (String) users.getUsername().subSequence(0, 1);
			model.addAttribute("UserInfor", firstChar.toUpperCase());
			
			UserEntity userEntity = usersRepository.findByUsername(users.getUsername());
			model.addAttribute("userEntity", userEntity);
			
			
			if(session.getAttribute("carts")==null) {
				model.addAttribute("null", "Giỏ hàng của bạn chưa có gì !");
				return "Product/Cart";
			}
			else {
				@SuppressWarnings("unchecked")
				List<Cart> carts = (List<Cart>) session.getAttribute("carts");
				model.addAttribute("listCarts", carts);
				return "Product/Cart";
			}
			
		}
		else {
			return "Product/Cart";
		}
		
		
	}

}
