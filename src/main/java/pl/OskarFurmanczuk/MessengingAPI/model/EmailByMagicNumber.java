package pl.OskarFurmanczuk.MessengingAPI.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Table("email_by_magic_number")
public class EmailByMagicNumber {
	
	@Column("id")
	private UUID id;
		
	@Column("email")
	private String email;
	
	@Column("title")
	private String title;
	
	@Column("content")
	private String content;
	
	@JsonProperty("magic_number")
	@PrimaryKey
	@Column("number")
	private int number;
	
	
	EmailByMagicNumber(int magic_number){
		this.number = magic_number;
	}

}
