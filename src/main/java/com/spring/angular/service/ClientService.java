package com.spring.angular.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.angular.models.Client;
import com.spring.angular.repository.ClientRepository;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	public Optional<Client> searchClientByDecodeur(String numero_decodeur){
		return clientRepository.findByDecodeur(numero_decodeur);
	}
}
