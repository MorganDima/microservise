package com.example.rentme_backend_morgan.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class JWTResponse {
	
	private String jwttoken;
	private String login;
	private List<String> roles;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date expiration;

}
