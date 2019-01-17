package pl.coderslab.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidationGroupFullUserValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidationGroupFullUser {

    String message() default "Niepoprawne imie lub nazwisko";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}