package com.xuezw.template.sys.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xuezw.template.sys.domain.User;
import com.xuezw.template.sys.security.JwtAuthenticationRequest;
import com.xuezw.template.sys.security.JwtAuthenticationResponse;

@RestController
public class AuthController {
	
	private final static Logger logger = LoggerFactory.getLogger(AuthController.class);
	
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
    	
    	logger.info("获取token的post请求，请求用户名为：" + authenticationRequest.getUsername());
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
    	
        String token = request.getHeader(tokenHeader);
        logger.info("刷新token的get请求，原token为： " + token);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

   @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public boolean register(@RequestBody User addedUser) throws AuthenticationException{
	   
	   logger.info("用户注册的post请求,注册用户为：" + addedUser.getUsername());
       return authService.register(addedUser);
    }
}