package com.example.rentme_backend_morgan.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Valid
@NotNull
@Getter

public class RoleLogin
{
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    @NotNull
    private String login;

    @JsonInclude(Include.NON_NULL)
    @Size(min = 5, max = 10)
    private String role;
}
