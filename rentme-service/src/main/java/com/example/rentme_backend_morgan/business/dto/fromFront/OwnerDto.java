package com.example.rentme_backend_morgan.business.dto.fromFront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerDto 
{
	String login;
	String email;
	String firstName;
	String lastName;
	String numberTelephone;
	String aboutMe;
	String photoOwner;

}
