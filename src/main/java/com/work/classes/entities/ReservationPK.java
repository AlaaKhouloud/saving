package com.work.classes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Embeddable
public @Data class ReservationPK implements Serializable{

	private int idClient;
	private int idTerrain;
	
	public ReservationPK(int idTerrain) {
		this.idTerrain = idTerrain;
	}

	public ReservationPK() {}

	public ReservationPK(int idClient, int idTerrain) {
		super();
		this.idClient = idClient;
		this.idTerrain = idTerrain;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationPK other = (ReservationPK) obj;
		if (idClient != other.idClient)
			return false;
		if (idTerrain != other.idTerrain)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClient;
		result = prime * result + idTerrain;
		return result;
	}
	
}
