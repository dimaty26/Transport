package org.ncstudy.transportservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "validation")
public class Validation {
	@Id
    @Column(name = "bus_stop_id", nullable = false)
    private String bus_stop_id;

    @Column(name = "card_id", nullable = false)
    private String card_id;

    public Validation() {
    }

    public Validation(String bus_stop_id, String card_id) {
        this.bus_stop_id = bus_stop_id;
        this.card_id = card_id;
    }

    public String getBus_stop_id() {
        return bus_stop_id;
    }

    public void setBus_stop_id(String bus_stop_id) {
        this.bus_stop_id = bus_stop_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
}
