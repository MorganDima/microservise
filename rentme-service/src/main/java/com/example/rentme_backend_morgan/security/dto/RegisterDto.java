package com.example.rentme_backend_morgan.security.dto;


import com.example.rentme_backend_morgan.security.validation.AccDtoCheck;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Valid
public class RegisterDto {

    @Size(min = 4, max = 15)
    @Pattern(regexp = "[A-Za-z0-9]+")
    private String login;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 5)
    private String password;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 4, max = 7)
//    @AccDtoCheck
    private String role;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 1, max = 50)
    private String firstName;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 1, max = 50)
    private String lastName;

    @JsonInclude(Include.NON_NULL)
    String numberTelephone;

    @JsonInclude(Include.NON_NULL)
    @Email
    String email;

    @JsonInclude(Include.NON_NULL)
    String aboutMe;

    @JsonInclude(Include.NON_NULL)
    @Size(max = 500000)
    String avatarPhoto;
}