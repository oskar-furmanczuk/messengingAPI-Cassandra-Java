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
public class EmailByEmailValue {
	
	@Column("id")
	private UUID id;
	
	@PrimaryKey	
	@Column("email")
	private String email;
	
	@Column("title")
	private String title;
	
	@Column("content")
	private String content;
	
	@JsonProperty("magic_number")
	@Column("number")
	private int number;
	
	
	EmailByEmailValue(int magic_number){
		this.number = magic_number;
	}

}
