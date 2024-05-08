package com.spring.angular;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.angular.config.RsaKeyProperties;
import com.spring.angular.enums.AccountStatus;
import com.spring.angular.models.Demande;
import com.spring.angular.models.Distributeur;
import com.spring.angular.models.Gerant;
import com.spring.angular.models.Role;
import com.spring.angular.service.DemandeService;
import com.spring.angular.service.DistributeurService;
import com.spring.angular.service.GerantService;
import com.spring.angular.service.RoleService;
import com.spring.angular.service.UtilisateurService;
import com.spring.angular.service.CanalService;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SpringwithangularApplication {
	
	//private static final Logger log = LoggerFactory.getLogger(SpringwithangularApplication.class);

	public static void main(String[] args) {
		
//		Timer timer;
//	    timer = new Timer();
//	    timer.schedule(new MaTask(), 1000, 5000);
		
		SpringApplication.run(SpringwithangularApplication.class, args);
	}
	
	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	
	//L100101 distrib
	//L104100 gerant
	
	 @Bean
	    CommandLineRunner start(
	    		RoleService roleService,GerantService gerantService,
	    		DistributeurService distributeurService, PasswordEncoder passwordEncoder,
	    		UtilisateurService utilisateurService,DemandeService demandeService,
	    		CanalService canalService){
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
	    		//System.out.println(randomString);
	    		
	    		String codeGerant = "GCUOuS";
	    		
	    		Optional<Gerant> existingGerant = gerantService.searchGerantByCode(codeGerant);
	    		
	    		//System.out.println("hello : "+ utilisateurService.searchByUserName("L104100").get());
	    		
	    		if(existingGerant.isEmpty()) {
	    			Gerant gerant = new Gerant();
		        	gerant.setNom("Jonhy");
		        	gerant.setPrenom("Pierre");
		        	gerant.setUsername("L104100");
		        	gerant.setCodeGerant(codeGerant);
		        	gerant.setPassword(passwordEncoder.encode("C@nalPlus!11"));
		        	gerant.setAccountStatus(AccountStatus.ACTIVATED);
		        	gerant.getRoles().add(existingRoleDistrib.get());
		        	gerant.getRoles().add(existingRoleGerant.get());
		        	gerantService.saveGerant(gerant);
	    		}
	    		
	    		Distributeur distributeur = (Distributeur) utilisateurService.searchByUserName("L100101").get();
	    		
	    		List<Demande> listeDemandes = new ArrayList<>();
	    		
	    		listeDemandes = demandeService.listeDemandeDistrib("reabonnement", distributeur);
	    		
	    		
	    		
	    		//System.out.println(canalService.commissionDistrib());
	    		
	    		//Calcule chiffre affaire
	    		
	        };
	    }

}
