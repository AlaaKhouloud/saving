package com.work.classes.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public @Data class Terrain implements Serializable{

	  @Id @GeneratedValue
	  private int idTerrain;
	  private String type;
	  private String description;
	  private String path_photo;
	  private double prix;
	  @OneToMany(fetch = FetchType.LAZY,mappedBy = "terrain" , cascade = CascadeType.ALL)
	  //@JsonIgnore
      private List<Reservation> reservations;
		
	  
	  public Terrain() {}
	  
	  public Terrain(String type , String description , String path_photo , double prix) {
		  this.type = type;
		  this.description = description;
		  this.path_photo = path_photo;
		  this.prix = prix;
	  }
	  
	  //for testing
	  public void assignReservationsToThisStade(List<Reservation> reservations) {
			this.setReservations(reservations);
			for (Reservation r : reservations) {
				r.setTerrain(this);
			}
	  }
}
