package com.spring.angular.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.experimental.var;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	RsaKeyProperties rsaKeyProperties;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
		
		daoAuthProvider.setPasswordEncoder(passwordEncoder);
	    daoAuthProvider.setUserDetailsService(userDetailsService);
		return new ProviderManager(daoAuthProvider);
	}

	@Bean
	public UserDetailsService inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				org.springframework.security.core.userdetails.User.withUsername("user1").password(passwordEncoder.encode("1234")).authorities("Distrib").build(),
				org.springframework.security.core.userdetails.User.withUsername("Gerant").password(passwordEncoder.encode("1234")).authorities("Gerant" , "Distrib").build()
		);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		DefaultBearerTokenResolver resolver = new DefaultBearerTokenResolver();
		resolver.setAllowFormEncodedBodyParameter(true);
		
		httpSecurity
			.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(auth -> auth.requestMatchers("/public/**").permitAll() )
			.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
	@Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.publicKey()).build();
        //return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }
	
	@Bean
    JwtEncoder jwtEncoder(){
        JWK jwk=new RSAKey.Builder(rsaKeyProperties.publicKey()).privateKey(rsaKeyProperties.privateKey()).build();
        //JWK jwk=new RSAKey.Builder((RSAPublicKey)keyPair.getPublic()).privateKey(keyPair.getPrivate()).build();
        JWKSource<SecurityContext> jwkSource=new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
}
