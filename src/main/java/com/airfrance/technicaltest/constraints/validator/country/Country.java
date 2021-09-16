package com.airfrance.technicaltest.constraints.validator.country;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CountryValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Country {
    String message() default "{country.not.valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
