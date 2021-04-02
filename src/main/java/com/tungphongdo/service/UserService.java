package com.tungphongdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.repository.UsersRepository;

@Service
public class UserService {
	@Autowired
	private UsersRepository usersRepository;
	
	public List<UserEntity> findbyUsername(String username){
		return usersRepository.findByUsernameLike("%"+username+"%");
	}

}
