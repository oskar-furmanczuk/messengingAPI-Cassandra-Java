package pl.OskarFurmanczuk.MessengingAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.OskarFurmanczuk.MessengingAPI.model.EmailByMagicNumber;
import pl.OskarFurmanczuk.MessengingAPI.service.EmailService;

@RequestMapping("/api")
@RestController
public class MainRestController {
	
	@Autowired
	public EmailService emailService;
	
	
	@GetMapping("/messages/{emailValue}")
	public ResponseEntity<List<EmailByMagicNumber>> getEmails(@PathVariable String emailValue) {
	
		try {
		    List<EmailByMagicNumber> _email = emailService.findByEmailValue(emailValue);
		    return new ResponseEntity<>(_email, HttpStatus.OK);
		    
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	@PostMapping("/message")
	public ResponseEntity<EmailByMagicNumber> createEmails(@RequestBody EmailByMagicNumber email) {
		
		try {
			EmailByMagicNumber _email = emailService.save(email);
		    return new ResponseEntity<>(_email, HttpStatus.CREATED);
		    
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	@PostMapping("/send")
	public ResponseEntity<List<EmailByMagicNumber>> sendEmails(@RequestBody EmailByMagicNumber email) {
		try {
		    List<EmailByMagicNumber> _emails = emailService.deleteByMagicNumber(email.getNumber());
		    return new ResponseEntity<>(_emails, HttpStatus.OK);
		    
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
}
