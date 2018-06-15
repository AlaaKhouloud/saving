package com.work.classes.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;



@Entity
public @Data class Reservation implements Serializable{

	
	@EmbeddedId
	private ReservationPK reservationPK;
	@ManyToOne
	@JoinColumn(insertable=false , updatable=false , name="idClient" , referencedColumnName = "idClient")
	private Visiteur visiteur;
	@ManyToOne
	@JoinColumn(insertable=false , updatable=false , name="idTerrain" , referencedColumnName = "idTerrain")
	private Terrain terrain;
	private String dateres;
	private String timeres;
	
	public Reservation() {
		super();
	}
	
	public Reservation(Visiteur visiteur, Terrain terrain, String date,String time) {
		super();
		this.visiteur = visiteur;
		this.terrain = terrain;
		this.dateres = date;
		this.timeres=time;
	}
	
	public Reservation(ReservationPK reservationPK, String date, String time) {
		super();
		this.reservationPK = reservationPK;
		this.dateres = date;
		this.timeres=time;
	}
	
    
}
