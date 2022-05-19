package com.example.rentme_backend_morgan.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class JWTRequest {
	
	private String login;
	private String password;

}
