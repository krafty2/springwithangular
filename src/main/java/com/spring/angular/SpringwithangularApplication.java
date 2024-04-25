package com.spring.angular;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.angular.config.RsaKeyProperties;
import com.spring.angular.models.Gerant;
import com.spring.angular.models.Role;
import com.spring.angular.service.RoleService;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SpringwithangularApplication {
	
	//private static final Logger log = LoggerFactory.getLogger(SpringwithangularApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringwithangularApplication.class, args);
	}
	
	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	 @Bean
	    CommandLineRunner start(RoleService roleService, PasswordEncoder passwordEncoder){
	        return args -> {
	        	String roleGerant = "Gerant";
	        	String roleDistrib = "Distributeur";
	        	
	        	Optional<Role> existingRoleGerant = roleService.searchByRoleName(roleGerant);
	        	Optional<Role> existingRoleDistrib = roleService.searchByRoleName(roleDistrib);
	        	
	        	if(existingRoleGerant.isEmpty()) {
	        		Role role = new Role();
	        		role.setRoleName(roleGerant);
	        		roleService.saveRole(role);
	        	}
	        	
	        	if(existingRoleDistrib.isEmpty()) {
	        		Role role = new Role();
	        		role.setRoleName(roleDistrib);
	        		roleService.saveRole(role);
	        	}
	        	
	        	Gerant gerant = new Gerant();
	        	gerant.setNom("Traore");
	        	gerant.setPrenom("Pierre");
	        	gerant.setPassword(passwordEncoder.encode("C@nalPlus!11"));
	        };
	    }

}
