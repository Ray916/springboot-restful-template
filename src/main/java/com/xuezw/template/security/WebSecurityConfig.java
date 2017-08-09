package com.xuezw.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.xuezw.template.service.CustomUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	/*@Autowired
	private UserDetailsService userDetailsService;*/
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder
								//.userDetailsService(this.userDetailsService)
								.userDetailsService(customUserService())
								.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public UserDetailsService customUserService(){
		return new CustomUserService();
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
		return new JwtAuthenticationTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserService());
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http	
				//由于使用JWT,这里不需要csrf
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				//基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET,
								"/",
								"/*.html",
								"/favicon.ico",
								"/**/*.css",
								"/**/*.js").permitAll()
					.antMatchers("/auth/**").permitAll()
					.anyRequest().authenticated();
		
				/*.authorizeRequests()
								.anyRequest().authenticated()
								.and()
								.formLogin()
									.loginPage("/login")
									.defaultSuccessUrl("/")
									.failureUrl("/login?error")
									.permitAll()
								.and()
								.logout().permitAll();*/
		
		//添加JWT filter
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		
		//禁用缓存
		http.headers().cacheControl();
	}
}
