package com.telecom.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telecom.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	boolean existsByPhoneNumber(String phoneNumber);

}
