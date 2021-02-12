package pl.OskarFurmanczuk.MessengingAPI.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.OskarFurmanczuk.MessengingAPI.model.EmailByEmailValue;

@Repository
public interface EmailByEmailValueRepository extends CassandraRepository<EmailByEmailValue, String> {
	
	@Query("select * from email_by_email_value where email=:email")
	List<EmailByEmailValue> findByEmail(@Param("email") String email);
	
}
