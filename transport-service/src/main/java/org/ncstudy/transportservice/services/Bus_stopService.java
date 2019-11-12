package org.ncstudy.transportservice.services;

import org.ncstudy.transportservice.model.Bus_stop;
import org.ncstudy.transportservice.model.Card;
import org.ncstudy.transportservice.repository.Bus_stopRepository;
import org.ncstudy.transportservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Bus_stopService {
    @Autowired
    private Bus_stopRepository bus_stopRepository;


    public List<Bus_stop> getAllBus_stops() {
        ArrayList<Bus_stop> list = new ArrayList<Bus_stop>();
        for (Bus_stop bus_stop : bus_stopRepository.findAll()) {
            list.add(bus_stop);
        }
        return list;
    }

    public void saveBus_stop(Bus_stop bus_stop){
        bus_stopRepository.save(bus_stop);
    }

    public void updateBus_stop(Bus_stop bus_stop){
        bus_stopRepository.save(bus_stop);

    }

    public void deleteBus_stop(UUID uuid){
        bus_stopRepository.deleteById(uuid);
    }
}
