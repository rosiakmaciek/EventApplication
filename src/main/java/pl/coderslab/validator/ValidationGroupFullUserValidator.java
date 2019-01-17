package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidationGroupFullUserValidator implements ConstraintValidator<ValidationGroupFullUser, String> {

    @Override
    public void initialize(ValidationGroupFullUser validationGroupFullUser) { }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

            String toValid = s;
            return !s.isEmpty();

    }
}