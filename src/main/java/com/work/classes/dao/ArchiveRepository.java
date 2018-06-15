package com.work.classes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.classes.entities.Archive;

public interface ArchiveRepository extends JpaRepository<Archive,Integer> {

	@Query("SELECT a FROM Archive a  where a.idterrain = :x1 AND dateres = :x2 ")
	public List<Archive> findByOptions(@Param("x1") int param1 , @Param("x2") String param2);
	
}
