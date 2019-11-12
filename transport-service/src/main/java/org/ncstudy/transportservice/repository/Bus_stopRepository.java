package org.ncstudy.transportservice.repository;


import org.ncstudy.transportservice.model.Bus_stop;
import org.ncstudy.transportservice.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Bus_stopRepository extends CrudRepository<Bus_stop, UUID> {
}
