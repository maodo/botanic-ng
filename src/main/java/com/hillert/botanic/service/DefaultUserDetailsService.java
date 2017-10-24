/*
 * Copyright 2014 the original author or authors.
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
package com.hillert.botanic.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hillert.botanic.dao.UsersRepository;
import com.hillert.botanic.model.CustomUserDetails;
import com.hillert.botanic.model.Users;
import org.springframework.stereotype.Service;
/**
*
* @author Gunnar Hillert
* @since 1.0
*
*/
@Service
public class DefaultUserDetailsService implements UserDetailsService {
	public static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserDetailsService.class);
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	
	private UsersRepository userRepository;
	
	public DefaultUserDetailsService() {
	}
	
	@Autowired
	public DefaultUserDetailsService(UsersRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<Users> optionalUsers = userRepository.findByUsername(username);
		optionalUsers.orElseThrow(()->new UsernameNotFoundException("Username not found"));
		 return optionalUsers
	                .map(CustomUserDetails::new).get();
	}
	
}
