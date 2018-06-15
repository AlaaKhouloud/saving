package com.work.classes.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.classes.MailSender.Mail;
import com.work.classes.dao.AbonneRepository;
import com.work.classes.entities.Abonner;

@RestController
public class AbonnerRepository {

	
	@Autowired
	private AbonneRepository abonnerepository;
	
	Mail mailing;
	
	//save
    @RequestMapping(value="/abonnement", method=RequestMethod.POST)
	public boolean save(String email) {
    	boolean ok = false;
    	try {
    		abonnerepository.save(new Abonner(email));
    		mailing= new Mail(email, "Club COC Newsletter","Vous vous etes inscrit à notre newsletter, merci de votre confiance!");
    		mailing.Send();
    		ok=true;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	return ok;
	}
}
