package com.example.rentme_backend_morgan.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleCheck implements ConstraintValidator<AccDtoCheck, String> {
    private String[] roles;

    @Override
    public void initialize(AccDtoCheck accDtoCheck) {
        this.roles = accDtoCheck.roles();
    }

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        return (role.equals(roles[0]) || role.equals(roles[1]));
    }
}