package com.work.classes.dao;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.classes.entities.Terrain;


public interface TerrainRepository extends JpaRepository<Terrain, Integer>{


	@Query("SELECT  t.description , t.type  FROM Terrain t where t.type = :x1")
	public Page<Terrain> getTerrainByType(@Param("x1") String type , Pageable pageable);
	
	
	@Query("SELECT  DISTINCT type  FROM Terrain t")
	public List<String> GetTerrain();
	
	
	@Query("SELECT  t FROM Terrain t where t.type = :x1")
	public Terrain findByName(@Param("x1") String name);
	
	
}
