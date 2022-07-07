package com.romTestProj.SOAWebAppTest.configuration;


import com.romTestProj.SOAWebAppTest.servicies.PersonDetailService;
import com.romTestProj.SOAWebAppTest.utils.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final PersonDetailService personDetailService;
	
	public SecurityConfiguration(PersonDetailService personDetailService) {
		this.personDetailService = personDetailService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
	 //config SpringSecurity ang user authorisation config
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login", "/error").permitAll() //unauthorized user can acces this pages
				.anyRequest().authenticated() //for all other pages user need to be authenticated
				.and()
				.formLogin().loginPage("/auth/login") //loginPage VieW
				.loginProcessingUrl("/login_exec") //from what address Spring would wait for user\login
				.defaultSuccessUrl("/hello", true) //redirect user after succesfull reg
				.failureUrl("/auth/login?error") //redirect if auth fail, with error param
				.failureHandler(authenticationFailureHandler())
				.and()
				.logout().logoutUrl("/logout")
				.logoutSuccessUrl("/auth/login")
		
		;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(personDetailService)//used by spring to check user in DB
				.passwordEncoder(getPasswordEncoder());
				
	}
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
}
