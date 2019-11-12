package org.ncstudy.transportservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus_stop")
public class Bus_stop {
    @Id
    @Column(name = "bus_stop_id", nullable = false)
    private String bus_stop_id;

    @Column(name = "bus_stop_name", nullable = false)
    private String bus_stop_name;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    public Bus_stop() {
    }

    public Bus_stop(String  bus_stop_id, String bus_stop_name, String latitude, String longitude) {
        this.bus_stop_id = bus_stop_id;
        this.bus_stop_name = bus_stop_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String  getBus_stop_id() {
        return bus_stop_id;
    }

    public void setBus_stop_id(String  bus_stop_id) {
        this.bus_stop_id = bus_stop_id;
    }

    public String getBus_stop_name() {
        return bus_stop_name;
    }

    public void setBus_stop_name(String bus_stop_name) {
        this.bus_stop_name = bus_stop_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
