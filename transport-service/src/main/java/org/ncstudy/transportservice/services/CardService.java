package org.ncstudy.transportservice.services;

import org.ncstudy.transportservice.model.Card;
import org.ncstudy.transportservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;


    public List<Card> getAllCards() {
        ArrayList<Card> list = new ArrayList<Card>();
        for (Card card : cardRepository.findAll()) {
            list.add(card);
        }
        return list;
    }

    public void saveCard(Card card){
        cardRepository.save(card);
    }

    public void updateCard(Card card){
        cardRepository.save(card);

    }

    public void deleteCard(UUID uuid){
        cardRepository.deleteById(uuid);
    }
}
