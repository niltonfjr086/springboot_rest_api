package com.apirest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);

		http.addFilterAfter(new CustomFilter()

		// Annonymous Way
		// new GenericFilterBean() {
		// @Override
		// public void doFilter(ServletRequest request, ServletResponse response,
		// FilterChain chain)
		// throws IOException, ServletException {
		//
		// chain.doFilter(request, response);
		//
		// }
		// }
				, BasicAuthenticationFilter.class);
	}

}