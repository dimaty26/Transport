package org.ncstudy.transportservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @Column(name = "id", nullable = false)
	private
    String id;

    @Column(name = "name", nullable = false)
	private
    String name;

    @Column(name = "latitude", nullable = false)
	private
    float latitude;

    @Column(name = "longitude", nullable = false)
	private
    float longitude;
    
    @Column(name = "workload", nullable = false)
	private
    int workload;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getWorkload() {
		return workload;
	}

	public void setWorkload(int workload) {
		this.workload = workload;
	}
}
