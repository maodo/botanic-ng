package com.hillert.botanic.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * Extends User and implements spring......UserDetails. 
 * We assume that the given roles are ADMIN,USER and not ROLE_ADMIN,ROLE_USER. 
 * In both database and Rest API exposed use of ADMIN,USER
 * @author a620036
 *
 */
public class CustomUserDetails extends Users implements UserDetails{
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetails.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	 public CustomUserDetails(final Users users) {
	        super(users);
	    }
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
