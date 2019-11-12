package org.ncstudy.transportservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "card_id", nullable = false)
    private String card_id;

    @Column(name = "password", nullable = false)
    private String password;

    public Card() {
    }

    public Card(String card_id, String password) {
        this.card_id = card_id;
        this.password = password;
    }

    public String  getCard_id() {
        return card_id;
    }

    public void setCard_id(String  card_id) {
        this.card_id = card_id;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
