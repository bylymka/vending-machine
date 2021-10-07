package com.imperva.vendingmachine.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {
	
	@NotBlank
	@Size(min = 4, max = 45)
	private String firstName;
	
	@NotBlank
	@Size(min = 4, max = 45)
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
}
