package com.work.classes.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.classes.dao.ArchiveRepository;
import com.work.classes.dao.TerrainRepository;
import com.work.classes.entities.Archive;
import com.work.classes.entities.Terrain;

@RestController
public class ArchiveRestService {

	@Autowired
	private TerrainRepository terrainRepository;
	
	@Autowired
	private ArchiveRepository archiveRepository;
	
	//show reservations of a specific date
	@RequestMapping(value="/search" , method=RequestMethod.GET)
	public List<Archive> find(String terrain , String date) throws ParseException {
		Terrain t = terrainRepository.findByName(terrain);
		List<Archive> reservations = archiveRepository.findByOptions(t.getIdTerrain() , date);
		return reservations;
	}
	
}
