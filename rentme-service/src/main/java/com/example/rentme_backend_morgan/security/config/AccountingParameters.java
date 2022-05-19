package com.example.rentme_backend_morgan.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AccountingParameters {

    @Value("${password_length:5}")
    private int passwordLength;

    @Value("${login_length_min:3}")
    private int loginLengthMin;

    @Value("${login_length_max:50}")
    private int loginLengthMax;

    @Value("${default_Role:USER}")
    private String defaultRole;

    @Value("${security.allowed_Role:ADMIN, MANAGER, OWNER, USER, GUEST}")
    private String[] allowedRoles;
    /*
//
ADMIN
MANAGER

OWNER
USER
GUEST

 */
    @Value("${security.expirationPeriod:30000}")
    private int expirationPeriod;

    @Value("${security.n_last_hashcodes:3}")
    private int n_last_hashcodes;

}
