package pl.OskarFurmanczuk.MessengingAPI.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.OskarFurmanczuk.MessengingAPI.model.EmailByMagicNumber;
import pl.OskarFurmanczuk.MessengingAPI.repository.EmailByEmailValueRepository;
import pl.OskarFurmanczuk.MessengingAPI.repository.EmailByMagicNumberRepository;


@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	public EmailByEmailValueRepository emailValueRepository;
	
	@Autowired
	public EmailByMagicNumberRepository magicNumberRepository;
	
	
	public EmailByMagicNumber save(EmailByMagicNumber email) {
		
		UUID newID = UUID.randomUUID();
		
		magicNumberRepository.save(
				new EmailByMagicNumber(
						newID,
						email.getEmail(),
						email.getTitle(),
						email.getContent(), 
						email.getNumber()
						));
		email.setId(newID);
		
		return email;
	}
	
	
	public List<EmailByMagicNumber> findByEmailValue(String emailValue){
		
		return emailValueRepository
				.findByEmail(emailValue)
				.stream()
				.map(o ->
						new EmailByMagicNumber(
								o.getId(),
								o.getEmail(),
								o.getTitle(),
								o.getContent(),
								o.getNumber())
						)
				.collect(Collectors.toList());
	}
	

	public List<EmailByMagicNumber> deleteByMagicNumber(int magicNumber){

		List <EmailByMagicNumber> emailsByMagicNumber = 
				magicNumberRepository.findByNumber(magicNumber);
		
		magicNumberRepository.deleteByNumber(magicNumber);

		return emailsByMagicNumber;
	}
	 

}
