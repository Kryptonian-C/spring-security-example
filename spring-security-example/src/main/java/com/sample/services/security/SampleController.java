package com.sample.services.security;
import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public String sample() {
		System.out.println("===");
		Collection<?extends GrantedAuthority> granted = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		 String role;
		 System.out.println(granted.size());
		for(int i=0;i<granted.size();i++){
	        role = granted.toArray()[i] + "";
	        System.out.println("role verified" + i + " is -> " + role);
	    }   
		
		return "Success";
	}
}
