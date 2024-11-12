package com.telecom.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.telecom.domain.model.Client;
import com.telecom.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@PostMapping
	public ResponseEntity<Client> create(@RequestBody Client clientToCreate) {
		Client createdClient = clientService.create(clientToCreate);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdClient.getId()).toUri();
		return ResponseEntity.created(location).body(createdClient);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
		Client client = clientService.findById(id);
		return ResponseEntity.ok(client);
	}

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> clients = clientService.findAll();
		return ResponseEntity.ok(clients);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody Client clientToUpdate) {
		Client updatedClient = clientService.update(id, clientToUpdate);
		return ResponseEntity.ok(updatedClient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
