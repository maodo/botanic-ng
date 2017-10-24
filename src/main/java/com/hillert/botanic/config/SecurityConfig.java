/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hillert.botanic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.hillert.botanic.dao.UsersRepository;
import com.hillert.botanic.service.DefaultUserDetailsService;

/**
 *
 * @author Gunnar Hillert
 * @since 1.0
 *
 */
@EnableWebMvcSecurity
@EnableWebSecurity(debug = false)
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
@Configuration
@Order
class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    private DefaultUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/plants/**").hasRole(DefaultUserDetailsService.ROLE_ADMIN);
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/plants", "/api/plants/**").hasRole(DefaultUserDetailsService.ROLE_ADMIN);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(userDetailsService);
//		authManagerBuilder.userDetailsService(new DefaultUserDetailsService());
//		authManagerBuilder.userDetailsService(new DefaultUserDetailsService()).passwordEncoder(getPasswordEncoder());
	}

	
//    private PasswordEncoder getPasswordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return true;
//            }
//        };
//    }
//	@Bean
//	@Override
//	public UserDetailsService userDetailsServiceBean() throws Exception {
//		return super.userDetailsServiceBean();
//	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

