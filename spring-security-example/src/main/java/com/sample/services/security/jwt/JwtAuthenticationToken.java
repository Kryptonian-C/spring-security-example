package com.sample.services.security.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	
	private final Object credential;
	private final Object principal;

	public JwtAuthenticationToken(Object credential, Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.credential = credential;
		this.principal = principal;
		super.setAuthenticated(true);
	}

	public JwtAuthenticationToken(String jwtToken, String userName) {
		super(null);
		this.credential=jwtToken;
		this.principal=userName;
		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return this.credential;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

}
