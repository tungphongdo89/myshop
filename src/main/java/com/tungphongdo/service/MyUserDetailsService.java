package com.tungphongdo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.repository.UsersRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UsersRepository usersRepository;
	
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException{
		UserEntity user = usersRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}
		
//		boolean enabled = true;
//		boolean accountNonExpired = true;
//		boolean credentialsNonExpired = true;
		
		
		if(user.getEnabled()==1) {
			CustomUserDetail customUserDetail = new CustomUserDetail();
			
			customUserDetail.setUserEntity(user);
//			return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
//					accountNonLocked, user.getAuthorities());
			return customUserDetail;
			
		}
		else {
			CustomUserDetail customUserDetail = new CustomUserDetail();
			customUserDetail.setUserEntity(user);
			@SuppressWarnings("unused")
			boolean accountNonLocked = false;
//			return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
//					accountNonLocked, user.getAuthorities());
			
			return customUserDetail;
		}
		
		
	}

}
