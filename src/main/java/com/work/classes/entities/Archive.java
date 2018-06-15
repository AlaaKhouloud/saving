package com.work.classes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Archive implements Serializable {

	@Id @GeneratedValue
	private int idArchive;
	private int idterrain;
	private int idVisiteur;
	private String dateres;
	private String timeres;
	
	public Archive() {}
	
	public Archive(int v , int t , String date , String time) {
		this.idVisiteur = v;
		this.idterrain = t;
		this.dateres = date;
		this.timeres = time;
	}
	
}
