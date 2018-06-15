package com.work.classes.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "Abonner", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public @Data  class Abonner {
	
	@Id @GeneratedValue
	private int idAbonnee;
	private String email; 
	
	public Abonner() {}
	public Abonner(String email) {
		this.email = email;
	}
}
