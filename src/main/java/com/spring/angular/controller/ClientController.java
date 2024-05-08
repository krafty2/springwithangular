package com.spring.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.models.Client;
import com.spring.angular.service.ClientService;

@RestController
@RequestMapping("/client/")
@CrossOrigin(origins = "*")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/all_clients")
	public List<Client> listeClient(){
		return clientService.allClients();
	}
}
