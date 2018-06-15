package com.work.classes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.work.classes.entities.Visiteur;

public interface ClientRepository extends JpaRepository<Visiteur, Integer>{

	//FindByEmail
 	@Query("SELECT idClient FROM Visiteur  where email = :x1 AND password = :x2 AND statut='manual'")
	public int FindByEmail(@Param("x1") String email , @Param("x2") String password); 
		
 	
 	
 	@Query("SELECT v FROM Visiteur v where v.email = :x1")
	public Visiteur FindIdByEmail(@Param("x1") String email);
}
