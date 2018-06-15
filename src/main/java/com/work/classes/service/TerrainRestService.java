package com.work.classes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.classes.dao.ReservationRepository;
import com.work.classes.dao.TerrainRepository;
import com.work.classes.entities.Terrain;

@RestController
public class TerrainRestService {

	@Autowired
	private TerrainRepository terrainRepository;
	
	
	//selection de tous les terrains
	@RequestMapping(value="/terrains", method=RequestMethod.GET)
	public Page<Terrain> listTerrains(int page , int size , String type) {
		return terrainRepository.getTerrainByType(type, new PageRequest(page, size));
	}
	
    //selection de tous les types de terrains
	@RequestMapping(value="/terrainType", method=RequestMethod.GET)
	public List<String> terrainType(){
		return terrainRepository.GetTerrain();
	}
	
	
	//search by name
	@RequestMapping(value="/findterrain",method=RequestMethod.GET)
	public Terrain find(String type){
		//terrainRepository.chercherTerrain("%"+type+"%" , "%"+reserved+"%");
		return terrainRepository.findByName(type);
	}
	
}
