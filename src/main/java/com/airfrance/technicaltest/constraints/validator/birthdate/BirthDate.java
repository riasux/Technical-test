package com.airfrance.technicaltest.constraints.validator.birthdate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BirthDate {
    String message() default "{date.birth.greaterOrEqualThan18}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
