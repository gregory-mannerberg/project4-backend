package com.skillstorm.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private String[] publicUrls = new String[] {
			"/register",
			"/logout",
			"/login"
	};
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.cors();
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers(publicUrls);
		http.authorizeHttpRequests().mvcMatchers("/login").permitAll();
		http.authorizeHttpRequests().mvcMatchers("/logout").permitAll();
		http.authorizeHttpRequests().mvcMatchers("/register").permitAll();
		http.authorizeHttpRequests().mvcMatchers("/**").authenticated();
		http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}

}
