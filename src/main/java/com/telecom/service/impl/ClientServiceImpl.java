package com.telecom.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.telecom.domain.model.Client;
import com.telecom.domain.repository.ClientRepository;
import com.telecom.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Client not found with id " + id));
	}

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client create(Client clientToCreate) {
		if (clientRepository.existsByPhoneNumber(clientToCreate.getPhoneNumber())) {
			throw new IllegalArgumentException("This phone number already exists.");
		}
		return clientRepository.save(clientToCreate);
	}

	@Override
	public Client update(Long id, Client clientToUpdate) {
		Client existingClient = findById(id);
		existingClient.setName(clientToUpdate.getName());
		existingClient.setPhoneNumber(clientToUpdate.getPhoneNumber());
		existingClient.setPlan(clientToUpdate.getPlan());
		existingClient.setCallRecords(clientToUpdate.getCallRecords());
		existingClient.setPayments(clientToUpdate.getPayments());
		return clientRepository.save(existingClient);
	}

	@Override
	public void delete(Long id) {
		Client client = findById(id);
		clientRepository.delete(client);
	}

}
