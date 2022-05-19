package com.example.rentme_backend_morgan.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    @Pattern(regexp = "^[A-Za-z0-9]*$")
    private String login;

    private String role;

}
