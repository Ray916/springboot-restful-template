package com.xuezw.template.sys.auth;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xuezw.template.sys.dao.SysUserRepository;
import com.xuezw.template.sys.domain.User;
import com.xuezw.template.sys.entity.SysRole;
import com.xuezw.template.sys.entity.SysUser;
import com.xuezw.template.sys.security.JwtTokenUtil;

@Service
public class AuthServiceImpl implements AuthService {

	    private AuthenticationManager authenticationManager;
	    private UserDetailsService userDetailsService;
	    private JwtTokenUtil jwtTokenUtil;
	    private SysUserRepository userRepository;

	    @Value("${jwt.tokenHead}")
	    private String tokenHead;

	    @Autowired
	    public AuthServiceImpl(
	            AuthenticationManager authenticationManager,
	            UserDetailsService userDetailsService,
	            JwtTokenUtil jwtTokenUtil,
	            SysUserRepository userRepository) {
	        this.authenticationManager = authenticationManager;
	        this.userDetailsService = userDetailsService;
	        this.jwtTokenUtil = jwtTokenUtil;
	        this.userRepository = userRepository;
	    }

	    @Override
	    public boolean register(User userToAdd) {
	        final String username = userToAdd.getUsername();
	        if(userRepository.findByUsername(username)!=null) {
	            return false;
	        }
	        SysUser sysuser = new SysUser();
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        final String rawPassword = userToAdd.getPassword();
	        sysuser.setUsername(userToAdd.getUsername());
	        
	        sysuser.setEmail(userToAdd.getEmail());
	        sysuser.setPassword(encoder.encode(rawPassword));
	        SysRole role = new SysRole();
	        role.setId(15L);
	        role.setName("user");
	        Set<SysRole> roles = new HashSet<SysRole>();
	        roles.add(role);
	        sysuser.setRoles(roles);
	        sysuser.setLastPasswordResetDate(new Date());
	        //添加角色
	        userRepository.save(sysuser);
	        return true;
	        
	    }

	    @Override
	    public String login(String username, String password) {
	        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
	        // Perform the security
	        final Authentication authentication = authenticationManager.authenticate(upToken);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Reload password post-security so we can generate token
	        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        final String token = jwtTokenUtil.generateToken(userDetails);
	        return token;
	    }

	    @Override
	    public String refresh(String oldToken) {
	        final String token = oldToken.substring(tokenHead.length());
	        String username = jwtTokenUtil.getUsernameFromToken(token);
	        SysUser user = (SysUser)userDetailsService.loadUserByUsername(username);
	        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
	            return jwtTokenUtil.refreshToken(token);
	        }
	        return null;
	    }
}
