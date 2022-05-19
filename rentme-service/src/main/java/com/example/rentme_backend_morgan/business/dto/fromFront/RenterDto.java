package com.example.rentme_backend_morgan.business.dto.fromFront;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RenterDto 
{
	String login;
	String firstName;
	String lastName;
	String numberTelephone;
	String email;
	String photo;
}
