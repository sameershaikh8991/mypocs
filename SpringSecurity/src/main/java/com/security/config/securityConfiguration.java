package com.security.config;



import javax.sql.DataSource;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.util.AuthenticationFilter;
import com.security.util.JwtFilter;


@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtFilter jwtFilter;
	
	AuthenticationFilter authenticationFilter;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
        .authorizeRequests()
        .antMatchers("/authenticate").permitAll()
         .anyRequest().authenticated()
         .and().sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterAfter(authenticationFilter, AnonymousAuthenticationFilter.class);
        
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	

}
