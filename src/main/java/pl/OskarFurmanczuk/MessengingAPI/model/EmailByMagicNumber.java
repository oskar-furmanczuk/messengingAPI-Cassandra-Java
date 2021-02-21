package pl.OskarFurmanczuk.MessengingAPI.model;

import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Email
	@NotNull
	@Size(min = 1, max = 20)
	private String email;
	
	@Column("title")
	@NotNull
	@Size(min = 3, max = 20)
	private String title;
	
	@Column("content")
	private String content;
	
	@JsonProperty("magic_number")
	@PrimaryKey
	@Column("number")
	@NotNull
	@Digits(fraction = 0, integer = 4)
	private int number;
	
	
}
