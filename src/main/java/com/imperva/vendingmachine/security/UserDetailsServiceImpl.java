package com.imperva.vendingmachine.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.model.Role;
import com.imperva.vendingmachine.model.User;
import com.imperva.vendingmachine.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
			return buildUserForAuthentication(user, authorities);
		}
		else
			log.info("User with email: " + email + " does not exist.");
		throw new UsernameNotFoundException("User with email: " + email + " does not exist.");
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){
		Set<GrantedAuthority> roles = new HashSet<>();
		
		userRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRole()));}
		);
		
		return new ArrayList<GrantedAuthority>(roles);
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities)
	{
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}
