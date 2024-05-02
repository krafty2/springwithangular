package com.spring.angular.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Autowired
	private JwtEncoder jwtEncoder;
	
	public Map<String, String> generateJwtToken(
			String username,Collection<? extends GrantedAuthority> authorities,
			boolean withRefreshToken
			) 
	{
		Map<String, String> idToken = new HashMap<>();
		Instant instant = Instant.now();
		String scope=authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken?35:30, ChronoUnit.MINUTES))
                .subject(username)
                .claim("scope",scope)
                .build();
		String accessToken = this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
		idToken.put("accessToken",accessToken);
		if(withRefreshToken){
            JwtClaimsSet jwtRefreshTokenClaimsSet=JwtClaimsSet.builder()
                    .issuer("auth-service")
                    .issuedAt(instant)
                    .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                    .subject(username)
                    .build();
            String refreshToken = this.jwtEncoder.encode(JwtEncoderParameters.from(jwtRefreshTokenClaimsSet)).getTokenValue();
            idToken.put("refreshToken",refreshToken);
        }
		return idToken;
	}
}
