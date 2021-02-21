package pl.OskarFurmanczuk.MessengingAPI.model;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MagicNumber {
	
	@JsonProperty("magic_number")
	@NotNull
	@Digits(fraction = 0, integer = 1000)
	private int number;

}
