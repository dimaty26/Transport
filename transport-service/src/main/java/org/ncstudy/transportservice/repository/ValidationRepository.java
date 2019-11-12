package org.ncstudy.transportservice.repository;


import org.ncstudy.transportservice.model.Card;
import org.ncstudy.transportservice.model.Validation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ValidationRepository extends CrudRepository<Validation, UUID> {
}
