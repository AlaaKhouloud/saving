package com.work.classes.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "Internaute", 
uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public @Data class Visiteur implements Serializable{

	@Id @GeneratedValue
	private int idClient;
	//@Pattern(regexp = "^[a-zA-Z]{40}$")
	private String nom_prenom; 
	private String password;
	//@Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
	private String email;
	//@Pattern(regexp="^$|[0-9]{10}")
	private String telephone;
	//@Pattern(regexp = "^[a-zA-Z]{40}$")
	private String statut;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "visiteur" , cascade = CascadeType.ALL)
	//@JsonIgnore
	private List<Reservation> reservations;
	
	
    public Visiteur() {}
    
    public Visiteur(String nom) {
    	this.nom_prenom = nom;
    }
    
    public Visiteur(String email , String telephone) {
    	this.email = email;
    	this.telephone= telephone;
    }
    
    public Visiteur(String nom_prenom , String email , String telephone , String password,String statut) {
    	this.nom_prenom = nom_prenom;
    	this.email = email;
    	this.telephone= telephone;
    	this.password = password;
    	this.statut = statut;
    }
    
    public Visiteur(int id , String nom_prenom , String email , String telephone , String password,String statut ,  List<Reservation> reservations) {
    	this.nom_prenom = nom_prenom;
    	this.email = email;
    	this.telephone= telephone;
    	this.password = password;
    	this.statut = statut;
    	this.idClient = id;
    	this.reservations = reservations;
    }
    
      //for testing
	  public void assignReservationsToThisClient(List<Reservation> reservations) {
			this.setReservations(reservations);
			for (Reservation r : reservations) {
				r.setVisiteur(this);
			}
	  }
}
