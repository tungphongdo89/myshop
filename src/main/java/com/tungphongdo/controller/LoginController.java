package com.tungphongdo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping
	public String Login(@RequestParam(required = false) String message, Model model, HttpSession httpSession) {
		if(message!=null && !message.isEmpty()) {
			if (message.equals("timeout")) {
		        model.addAttribute("message", "Đăng xuất thành công");
		      }
		      if (message.equals("max_session")) {
		        model.addAttribute("message", "Tài khoản này đang được sử dụng ở một thiết bị khác!");
		      }

			if(message.equals("logout")) {
				model.addAttribute("message", "Đăng xuất thành công !");
			}
			if(message.equals("error")) {
				model.addAttribute("message", "Đăng nhập thất bại !");
				model.addAttribute("message2", "Tài khoản của bạn đã bị khóa hoặc không tồn tại !");
			}
		}
		
		return "Login/Login";	
	}


}
