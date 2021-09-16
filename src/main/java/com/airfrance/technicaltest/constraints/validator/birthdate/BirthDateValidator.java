package com.airfrance.technicaltest.constraints.validator.birthdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;

public class BirthDateValidator implements ConstraintValidator<BirthDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate valueToValidate, final ConstraintValidatorContext context) {
        if (valueToValidate == null) {
            return false;
        }
        return Period.between(valueToValidate, LocalDate.now(ZoneOffset.UTC)).getYears() >= 18;
    }
}
