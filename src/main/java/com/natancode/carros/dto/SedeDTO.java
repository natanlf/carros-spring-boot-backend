package com.natancode.carros.dto;

import java.io.Serializable;

public class SedeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Double lat;
 	private Double log;
 	
 	public SedeDTO() {}
 	
	public SedeDTO(Integer id, Double lat, Double log) {
		super();
		this.id = id;
		this.lat = lat;
		this.log = log;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLog() {
		return log;
	}

	public void setLog(Double log) {
		this.log = log;
	}

}
