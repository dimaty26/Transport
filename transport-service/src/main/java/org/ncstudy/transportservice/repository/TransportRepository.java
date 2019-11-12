package org.ncstudy.transportservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import org.ncstudy.transportservice.model.Transport;

@Repository
public interface TransportRepository extends CrudRepository<Transport,UUID>  {
	//Базовые операции уже реализованы, ничего определять не нужно, см TransportService
}
