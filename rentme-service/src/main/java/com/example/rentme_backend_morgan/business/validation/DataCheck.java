package com.example.rentme_backend_morgan.business.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataValidator.class)
public @interface DataCheck {

    int maxYear() default 12;

    String message() default "Available date out of range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}