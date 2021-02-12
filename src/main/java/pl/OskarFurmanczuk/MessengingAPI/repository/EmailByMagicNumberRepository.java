package pl.OskarFurmanczuk.MessengingAPI.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.OskarFurmanczuk.MessengingAPI.model.EmailByMagicNumber;

@Repository
public interface EmailByMagicNumberRepository extends CassandraRepository<EmailByMagicNumber, Integer> {
		
	List<EmailByMagicNumber> findByNumber(@Param("nr") int nr);
	
	@Query("delete from email_by_magic_number where number=:nr")
	List <EmailByMagicNumber> deleteByNumber(@Param("nr") int magicNumber);

}
