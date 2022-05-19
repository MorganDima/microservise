package com.example.rentme_backend_morgan.security.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleCheck.class)
public @interface AccDtoCheck {

    String[] roles() default {"USER", "OWNER"};

    String message() default "Role dont exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}