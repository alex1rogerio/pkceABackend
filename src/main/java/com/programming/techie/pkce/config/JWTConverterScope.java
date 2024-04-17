package com.programming.techie.pkce.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

public class JWTConverterScope implements Converter<Jwt, AbstractAuthenticationToken>{

	JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter= new JwtGrantedAuthoritiesConverter();	

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {		
		
		Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
		
		
		Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
		Collection<String> roles = realmAccess.get("roles");
		
		var grants = roles	
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role)).toList();
		
		
		List<GrantedAuthority> lstGrantedAuthority = new ArrayList<GrantedAuthority>();
		lstGrantedAuthority.addAll(authorities);
		lstGrantedAuthority.addAll(grants);		
		
		return new JwtAuthenticationToken(jwt, lstGrantedAuthority);
	}

}
