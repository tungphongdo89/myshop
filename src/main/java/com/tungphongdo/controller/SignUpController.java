package com.tungphongdo.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tungphongdo.entity.RoleEntity;
import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.entity.UserRoleEntity;
import com.tungphongdo.repository.RoleRepository;
import com.tungphongdo.repository.UserRoleRepository;
import com.tungphongdo.repository.UsersRepository;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping
	public String doSignUp(HttpServletRequest request, Model model) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setAddress(request.getParameter("address").trim());
		userEntity.setEmail(request.getParameter("email").trim());
		userEntity.setEnabled(1);
		userEntity.setGender(request.getParameter("gender").trim());
		String pass = BCrypt.hashpw(request.getParameter("pass").trim(), BCrypt.gensalt(12));
		userEntity.setPassword(pass);
		userEntity.setTelephone(Long.parseLong(request.getParameter("phone").trim()));
		userEntity.setUsername(request.getParameter("username").trim());
		
		usersRepository.save(userEntity);
		
		RoleEntity roleEntity = new RoleEntity();
		roleEntity  = roleRepository.findById(2).get();
		
		UserRoleEntity userRoleEntity = new UserRoleEntity(userEntity, roleEntity);
		userRoleRepository.save(userRoleEntity);
		return "Login/Login";
	}
	
//	@PostMapping
//	public String doSignUp(@ModelAttribute("UserEntity") UserEntity userEntity2, Model model) {
//		UserEntity userEntity = new UserEntity();
//		userEntity.setAddress(userEntity2.getAddress());
//		userEntity.setEmail(userEntity2.getEmail());
//		userEntity.setEnabled(1);
//		userEntity.setGender(userEntity2.getGender());
//		String pass = BCrypt.hashpw(userEntity2.getPassword(), BCrypt.gensalt(12));
//		userEntity.setPassword(pass);
//		userEntity.setTelephone(userEntity2.getTelephone());
//		userEntity.setUsername(userEntity2.getUsername());
//		
//		usersRepository.save(userEntity);
//		
//		return "Login/Login";
//	}

}
