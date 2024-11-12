package com.telecom.service;

import java.util.List;

import com.telecom.domain.model.Client;

public interface ClientService {

	Client findById(Long id);
	
	List<Client> findAll();
	
	Client create(Client clientToCreate);
	
	Client update(Long id, Client clientToUpdate);
	
	void delete(Long id);
}
