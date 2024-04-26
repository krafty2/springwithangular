package com.spring.angular;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.angular.config.RsaKeyProperties;
import com.spring.angular.enums.AccountStatus;
import com.spring.angular.models.Distributeur;
import com.spring.angular.models.Gerant;
import com.spring.angular.models.Role;
import com.spring.angular.service.DistributeurService;
import com.spring.angular.service.GerantService;
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
	    CommandLineRunner start(
	    		RoleService roleService,GerantService gerantService,
	    		DistributeurService distributeurService, PasswordEncoder passwordEncoder){
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
	        	String randomString = RandomStringUtils.randomAlphanumeric(5);
	    		System.out.println(randomString);
	    		
	    		String codeGerant = "GCUOuS";
	    		String codeDistrib = "D9uYSs";
	    		
	    		Optional<Gerant> existingGerant = gerantService.searchGerantByCode(codeGerant);
	    		
	    		Optional<Distributeur> existingDistributueur = distributeurService.searchDistribByCode(codeDistrib);
	    		
	    		if(existingGerant.isEmpty()) {
	    			Gerant gerant = new Gerant();
		        	gerant.setNom("Jonhy");
		        	gerant.setPrenom("Pierre");
		        	gerant.setCodeGerant(codeGerant);
		        	gerant.setPassword(passwordEncoder.encode("C@nalPlus!11"));
		        	gerant.setAccountStatus(AccountStatus.ACTIVATED);
		        	gerant.getRoles().add(existingRoleDistrib.get());
		        	gerant.getRoles().add(existingRoleGerant.get());
		        	gerantService.saveGerant(gerant);
	    		}
	    		
	    		if(existingDistributueur.isEmpty()) {
	    			Distributeur distributeur = new Distributeur();
	    			
	    			distributeur.setNom("Sandy");
	    			distributeur.setPrenom("Jean");
	    			distributeur.setCodeDistributeur(codeDistrib);
	    			distributeur.setPassword(passwordEncoder.encode("Distrib2024!"));
	    			distributeur.setAccountStatus(AccountStatus.ACTIVATED);
	    			distributeur.setLongitude(11.4533);
	    			distributeur.setLatitude(-4.5644);
	    			distributeur.getRoles().add(existingRoleDistrib.get());	    		
	    			
	    			distributeurService.saveDistributeur(distributeur);
	    		}
	        	
	    		
	        };
	    }

}
