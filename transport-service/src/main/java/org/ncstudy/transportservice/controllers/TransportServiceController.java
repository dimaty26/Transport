package org.ncstudy.transportservice.controllers;


import org.ncstudy.transportservice.model.Bus_stop;
import org.ncstudy.transportservice.model.Card;
import org.ncstudy.transportservice.model.Transport;
import org.ncstudy.transportservice.repository.CardRepository;
import org.ncstudy.transportservice.services.Bus_stopService;
import org.ncstudy.transportservice.services.CardService;
import org.ncstudy.transportservice.services.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value="api/v1")
public class TransportServiceController {
	//TRANSPORT
	@Autowired
    private TransportService transportService;
    private static final Logger logger = LoggerFactory.getLogger(TransportServiceController.class);


	@RequestMapping(value="/transport",method = RequestMethod.GET)
    public List<Transport> getAllTransports() {
        logger.debug("Looking up data for transports...");
        List<Transport> list = transportService.getAllTransports();
        logger.debug("Found {} transport entries.", list.size());
        return list;
    }

    @RequestMapping(value="/transport",method = RequestMethod.POST)
    public void saveTransport(@RequestBody Transport transport) {
       transportService.saveTransport(transport);
    }
    
    @RequestMapping(value="/transport",method = RequestMethod.PUT)
    public void updateTransport(@RequestBody Transport transport) {
        transportService.saveTransport(transport);

    }

    @RequestMapping(value="/transport/{transportId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransport( @PathVariable("transportId") UUID transportId) {
        transportService.deleteTransport( transportId );
    }


    //CARD
    @Autowired
    private CardService cardService;

    @RequestMapping(value="/card",method = RequestMethod.GET)
    public List<Card> getAllCards() {
        List<Card> list = cardService.getAllCards();
        return list;
    }


    @RequestMapping(value="/card",method = RequestMethod.POST)
    public void saveCard(@RequestBody Card card) {
        cardService.saveCard(card);
    }

    @RequestMapping(value="/card",method = RequestMethod.PUT)
    public void updateCard(@RequestBody Card card) {
        cardService.saveCard(card);

    }

    @RequestMapping(value="/card/{card_id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard( @PathVariable("card_id") UUID card_id) {
        cardService.deleteCard( card_id );
    }


    //BUS
    @Autowired
    private Bus_stopService bus_stopService;

    @RequestMapping(value="/bus_stop",method = RequestMethod.GET)
    public List<Bus_stop> getAllBus_stops() {
        List<Bus_stop> list = bus_stopService.getAllBus_stops();
        return list;
    }


    @RequestMapping(value="/bus_stop",method = RequestMethod.POST)
    public void saveBus_stop(@RequestBody Bus_stop bus_stop) {
        bus_stopService.saveBus_stop(bus_stop);
    }

    @RequestMapping(value="/bus_stop",method = RequestMethod.PUT)
    public void updateBus_stop(@RequestBody Bus_stop bus_stop) {
        bus_stopService.saveBus_stop(bus_stop);

    }

    @RequestMapping(value="/bus_stop/{bus_stop_id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBus_stop( @PathVariable("bus_stop_id") UUID bus_stop_id) {
        bus_stopService.deleteBus_stop( bus_stop_id );
    }
}
