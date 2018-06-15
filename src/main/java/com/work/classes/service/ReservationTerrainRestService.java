package com.work.classes.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.classes.dao.ClientRepository;
import com.work.classes.dao.ReservationRepository;
import com.work.classes.dao.TerrainRepository;
import com.work.classes.entities.Reservation;
import com.work.classes.entities.ReservationPK;
import com.work.classes.entities.Terrain;
import com.work.classes.entities.Visiteur;

@RestController
public class ReservationTerrainRestService {

	@Autowired
	private TerrainRepository terrainRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	//for the test
	public ReservationTerrainRestService( ReservationRepository reservationRepository) {
		this.reservationRepository=reservationRepository;
	}
	
	
	//passer une reservation
    @RequestMapping(value="/reserve", method=RequestMethod.POST)
	public Reservation Findclientid(String email, int idterrain , String dateres , String timeres) {
    	Visiteur client = null;
    	Terrain terrain = null;
    	client =  clientRepository.FindIdByEmail(email);
    	terrain = terrainRepository.findOne(idterrain);
    	//see if exists delete+archive old save new
    	Reservation res = reservationRepository.findOne(new ReservationPK(client.getIdClient() , terrain.getIdTerrain()));
    	if(res != null) {
    		reservationRepository.delete(res);
    	}
         return reservationRepository.save(new Reservation(client,terrain , dateres , timeres));
	}
	
	
	
}
