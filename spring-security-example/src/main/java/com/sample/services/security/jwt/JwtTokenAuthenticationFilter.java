package com.sample.services.security.jwt;

import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwtToken = null;
		final String requestTokenHeader = request.getHeader("Authorization");
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
		}

		SecretKeySpec keySpec = new SecretKeySpec("SECRET_KEY_TO_GENERATE_JWT_SAMPLE_TEST".getBytes(), "HmacSHA256");
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(keySpec).build();
		String userName = jwtParser.parseClaimsJws(jwtToken).getBody().getSubject();
		
		SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwtToken, userName));
		
		filterChain.doFilter(request, response);
	}

}
