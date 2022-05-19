package com.example.rentme_backend_morgan.business.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DataValidator implements ConstraintValidator<DataCheck, LocalDate> {

    private int maxYear;

    @Override
    public void initialize(DataCheck dataCheck) {
        this.maxYear = dataCheck.maxYear();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        return !value.isBefore(now) || value.getYear() < maxYear;

    }
}
