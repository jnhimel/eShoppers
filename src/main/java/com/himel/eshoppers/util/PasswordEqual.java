package com.himel.eshoppers.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordEqualValidator.class)
public @interface PasswordEqual {
    String message() default "The fields much match";
    String first();
    String second();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
