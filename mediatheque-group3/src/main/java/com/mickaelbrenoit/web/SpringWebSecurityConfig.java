package com.mickaelbrenoit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.mickaelbrenoit.web.exception.CustomAccessDeniedHandler;
import com.mickaelbrenoit.web.security.SecurityUserDetailsService;

@Configuration
// TODO enable SpringSecurity
@EnableWebSecurity
// TODO Enable method security
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

	// TODO enable Users in DB
	@Autowired
	private SecurityUserDetailsService userDetailsService;

	// TODO Enable SpringSecurity
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// TODO enable SpringSecurity
		http.authorizeRequests().antMatchers("/**").permitAll();

		// TODO enable SpringSecurity
//		http.authorizeRequests().antMatchers("/").permitAll();
//		http.authorizeRequests().antMatchers("/signin/**").permitAll();
//		http.authorizeRequests().antMatchers("/signup/**").permitAll();
//		http.authorizeRequests().antMatchers("/home/**").permitAll();
//		http.authorizeRequests().antMatchers("/person*").access("hasAuthority('ADMIN')");
/*		http.authorizeRequests().antMatchers("/profile*").hasRole("USER");*/

/*		http.authorizeRequests().antMatchers("/login/**").permitAll();*/
		
//		http.formLogin().loginPage("/home").permitAll();
		http.logout().logoutSuccessUrl("/");

		http.authorizeRequests().anyRequest().authenticated();
		
		// TODO enable SSL
//		http.requiresChannel().antMatchers("/").requiresInsecure();
//		http.requiresChannel().antMatchers("/home").requiresInsecure();
		http.requiresChannel().anyRequest().requiresSecure();

		// TODO enable Exception handling
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		
		// TODO Enable session management
		http.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        	.sessionFixation().migrateSession();
		
		// TODO manage headers
		http.headers()
			.frameOptions().sameOrigin()
			.httpStrictTransportSecurity().disable();
		
	}

	// TODO enable more things : CSRF ?
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	// TODO enable Users in DB
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//		 		.withUser("user").password("user").roles("USER")
//		 		.and().withUser("admin").password("admin").roles("ADMIN");
//	}
	
	// TODO enable Users in DB
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

//	 TODO enable Exception handling
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

}
