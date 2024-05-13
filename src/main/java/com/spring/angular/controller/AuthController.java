package com.spring.angular.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.models.Utilisateur;
import com.spring.angular.service.TokenService;
import com.spring.angular.service.UtilisateurService;

@RestController
@RequestMapping("/public/")
@CrossOrigin(origins = "*")
public class AuthController {

	private JwtEncoder jwtEncoder;
	@Autowired
	private JwtDecoder jwtDecoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	UtilisateurService utilisateurService;

	public AuthController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager) {
		super();
		this.jwtEncoder = jwtEncoder;
		this.authenticationManager = authenticationManager;
	}

	// @PostMapping("/token")
	// public ResponseEntity<Map<String,String>> requestForToken(
	// @RequestParam String username,@RequestParam String password,
	// @RequestParam boolean withRefreshToken,@RequestParam String grandType,
	// @RequestParam String refreshToken
	// ){
	//
	// System.out.println("hello");
	//
	// String subject = null;
	// String scope = null;
	// //Authentication authentication = null;
	//
	// if(grandType.equals("password")) {
	// Authentication authentication = authenticationManager
	// .authenticate(
	// new UsernamePasswordAuthenticationToken(username, password));
	// subject = authentication.getName();
	// scope = authentication.getAuthorities()
	// .stream().map(auth->auth.getAuthority())
	// .collect(Collectors.joining(" "));
	// } else if(grandType.equals("refreshToken")) {
	// Jwt decode = null;
	//
	// try {
	// decode = jwtDecoder.decode(refreshToken);
	// } catch (Exception e) {
	// // TODO: handle exception
	// return new
	// ResponseEntity<>(Map.of("message",e.getMessage()),HttpStatus.UNAUTHORIZED);
	// }
	//
	// subject = decode.getSubject();
	// UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
	//
	// java.util.Collection<?extends GrantedAuthority> autoritiesCollection =
	// userDetails.getAuthorities();
	//
	// scope = autoritiesCollection
	// .stream().map(auth->auth.getAuthority())
	// .collect(Collectors.joining(" "));
	// }
	//
	// Instant instant = Instant.now();
	//
	// Map<String, String> response = new HashMap<>();
	//
	//// String scopeString = authentication.getAuthorities()
	//// .stream().map(auth->auth.getAuthority())
	//// .collect(Collectors.joining(" "));
	//
	// JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
	// .subject(subject)
	// .issuedAt(instant)
	// .expiresAt(instant.plus(withRefreshToken? 5:30,ChronoUnit.MINUTES))
	// .issuer("security-service")
	// .claim("scope", scope)
	// .build();
	//
	// String jwtAccessToken =
	// jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
	//
	// response.put("accessTokken", jwtAccessToken);
	//
	// if(withRefreshToken) {
	// JwtClaimsSet jwtClaimsSetRefreshToken = JwtClaimsSet.builder()
	// .subject(subject)
	// .issuedAt(instant)
	// .expiresAt(instant.plus(30,ChronoUnit.MINUTES))
	// .issuer("security-service")
	// .build();
	//
	// String jwtAccessRefreshToken =
	// jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefreshToken)).getTokenValue();
	// response.put("refreshTokken", jwtAccessRefreshToken);
	// }
	//
	// return new ResponseEntity<>(response,HttpStatus.OK);
	//
	// }

	@PostMapping("/token")
	public ResponseEntity<Map<String, String>> requestForToken(
			@RequestParam String username, @RequestParam String password,
			@RequestParam boolean withRefreshToken, @RequestParam String grandType,
			@RequestParam String refreshToken) {

		Map<String, String> response;

		if (grandType.equals("password")) {
			System.out.println("non");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							username, password));

			System.out.println("hello" + authentication);

			response = tokenService.generateJwtToken(authentication.getName(), authentication.getAuthorities(),
					withRefreshToken);
			return ResponseEntity.ok(response);
		} else if (grandType.equals("refreshToken")) {
			String refreshTokens = refreshToken;
			if (refreshTokens == null) {
				return new ResponseEntity<>(Map.of("error", "RefreshToken Not Present"), HttpStatus.UNAUTHORIZED);
			}
			Jwt decodedJwt = jwtDecoder.decode(refreshTokens);
			String username1 = decodedJwt.getSubject();
			Utilisateur appUser = utilisateurService.searchByUserName(username1).get();
			Collection<GrantedAuthority> authorities = appUser.getRoles()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
					.collect(Collectors.toList());
			System.out.println(authorities);
			response = tokenService.generateJwtToken(appUser.getNom(), authorities, withRefreshToken);
			return ResponseEntity.ok(response);
		}
		
		return new ResponseEntity<Map<String, String>>(
				Map.of("error", String.format("grantType <<%s>> not supported ", grandType)), HttpStatus.UNAUTHORIZED);
	}

}
