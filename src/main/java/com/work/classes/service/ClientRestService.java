package com.work.classes.service;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.work.classes.dao.ClientRepository;
import com.work.classes.dao.TerrainRepository;
import com.work.classes.entities.Visiteur;

@RestController
public class ClientRestService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private TerrainRepository terrainRepository;
	
	
	    //find by email and mdp
	    @RequestMapping(value="/checkE", method=RequestMethod.GET)
		public int Find(String email , String password) {
	    	int id= 0;
	    	try {
			    id =  clientRepository.FindByEmail(email,password);
	    	}catch(Exception ex) {
	    		System.out.println(ex.getMessage());
	    	}
	    	return id;
		}
	    
	    //find by email
	    @RequestMapping(value="/clientid", method=RequestMethod.GET)
		public Visiteur Findclientid(String email) {
	    	Visiteur v= null;
	    	try {
			    v =  clientRepository.FindIdByEmail(email);
	    	}catch(Exception ex) {
	    		System.out.println(ex.getMessage());
	    	}
	    	return v;
		}
	    
	    //save
	    @RequestMapping(value="/save", method=RequestMethod.POST)
		public boolean save(String nom , String email , String tel , String password , String statut) {
	    	boolean ok = false;
	    	try {
	    		clientRepository.save(new Visiteur(nom , email , tel , password , statut));
	    		ok=true;
	    	}
	    	catch(Exception ex) {
	    		System.out.println(ex.getMessage());
	    	}
	    	return ok;
		}
	
	/* 
		
		//single selection
		//@Secured(value= {"ROLE_ADMIN","ROLE_CLIENT"})
		@RequestMapping(value="/clients/{id}",method=RequestMethod.GET)
		public Visiteur getClient(@PathVariable ("id")int id){
		   return clientRepository.findOne(id);	
		}
		
		//insertion
		//@Secured(value= {"ROLE_VISITEUR"})
		@RequestMapping(value="/clients" , method=RequestMethod.POST)
		public Object saveClients(@RequestBody @Valid Visiteur v , BindingResult bindingResult) {
			if(bindingResult.hasErrors()) {
				Map<String,Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe :  bindingResult.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return errors;
			}
			return clientRepository.save(v);
		}

		*/
}
