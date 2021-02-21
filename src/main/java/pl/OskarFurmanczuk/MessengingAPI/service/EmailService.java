package pl.OskarFurmanczuk.MessengingAPI.service;

import java.util.List;

import pl.OskarFurmanczuk.MessengingAPI.model.EmailByMagicNumber;

public interface EmailService {
	public EmailByMagicNumber save(EmailByMagicNumber email);
	
	
	public List<EmailByMagicNumber> findByEmailValue(String emailValue);
	

	public List<EmailByMagicNumber> deleteByMagicNumber(int magicNumber);
	 

}
