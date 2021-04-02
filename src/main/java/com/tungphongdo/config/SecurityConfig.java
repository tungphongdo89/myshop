package com.tungphongdo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.tungphongdo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	//Bean httpSessionEventPublisher để enable khả năng hỗ trợ concurrent session-control.
	@Bean
	  public HttpSessionEventPublisher httpSessionEventPublisher() {
	      return new HttpSessionEventPublisher();
	  }

	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService( userDetailsService).passwordEncoder(passwordEncoder());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Cấu hình concurrent session
	    http.sessionManagement().sessionFixation().newSession()
	    .invalidSessionUrl("/login?message=timeout")
	    .maximumSessions(1).expiredUrl("/login?message=max_session").maxSessionsPreventsLogin(true);


		// Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
	    //http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/admin/**").permitAll(); // tất cả các quyền đều được truy cập

		//http.authorizeRequests().antMatchers("/home/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')");
		http.authorizeRequests().antMatchers("/home/**").permitAll();

		// Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang
		// yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		

		// Cấu hình remember me, thời gian là 1296000 giây
	    http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
		

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/j_spring_security_login")//
				.loginPage("/login")//
				.defaultSuccessUrl("/home")//
				//.failureUrl("/login?message=error")//
				.failureHandler(customAuthenticationFailureHandler)
				.usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout"); 
	}
	

}
