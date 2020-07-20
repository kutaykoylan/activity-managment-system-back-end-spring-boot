package com.tubitak.activitybackend.services.userservice.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TCSecurityNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TCSecurityNumber {
    String message() default "Tc kimlik numarası formatı uygun değildir";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
