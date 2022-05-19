package com.example.rentme_backend_morgan.business.dto.fromFront;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
public class EditProfileDto {

    String login;

    @Pattern(regexp = "^[A-Za-z]*$")
    String firstName;

    @Pattern(regexp = "^[A-Za-z]*$")
    String lastName;

    @JsonInclude(Include.NON_NULL)
    @Pattern(regexp = "^[+0-9]*$")
    String numberTelephone;

    @JsonInclude(Include.NON_NULL)
    @Email
    String email;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 100_000, max = 500_000)
    String photo;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 10, max = 170)
    String aboutMe;

}