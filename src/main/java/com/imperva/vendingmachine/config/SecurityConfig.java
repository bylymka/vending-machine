package com.imperva.vendingmachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imperva.vendingmachine.security.MyAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler()
	{
		return new MyAuthenticationSuccessHandler();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/login", "/api/v1/user/**").permitAll()
				.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/login/success")
				.failureUrl("/login?error=true")
				.successHandler(myAuthenticationSuccessHandler())
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.logoutSuccessUrl("/login?logout=true");
	}
}
