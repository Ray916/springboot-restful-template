package com.xuezw.template.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xuezw.template.sys.dao.SysUserRepository;
import com.xuezw.template.sys.entity.SysUser;

public class CustomUserService implements UserDetailsService {

	@Autowired
	SysUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		SysUser user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
		System.out.println(user.toString());
		return user;
	}

}
