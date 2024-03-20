package com.metaenlace.clinicaapplication.model.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@Schema(
			description = "User name",
			example = "John")
	private String name;
	
	@Schema(
			description = "User surname",
			example = "Doe")
	private String surname;
	
	@Schema(
			description = "User username",
			example = "johndoe123")
	private String username;
	
	@Schema(
			description = "User password",
			example = "password123")
	private String passcode;
}
