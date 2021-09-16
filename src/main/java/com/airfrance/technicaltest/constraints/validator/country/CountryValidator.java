package com.airfrance.technicaltest.constraints.validator.country;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidator implements ConstraintValidator<Country, String> {


    @Override
    public boolean isValid(String countryDisplayName, ConstraintValidatorContext context) {
        return countryDisplayName != null && countryDisplayName.trim().equalsIgnoreCase("France");
    }
}
