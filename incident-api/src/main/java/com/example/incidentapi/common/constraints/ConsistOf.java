package com.example.incidentapi.common.constraints;


import org.terasoluna.gfw.common.codepoints.CodePoints;
import org.terasoluna.gfw.common.codepoints.validator.ConsistOfValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(ConsistOf.List.class)
@Constraint(validatedBy = {ConsistOfValidator.class})
@Documented
public @interface ConsistOf {

    /**
     * CodePoints
     *
     * @return codePoints
     */
    Class<? extends CodePoints>[] value();

    /**
     * Error message or message key
     *
     * @return error message or message key
     */
    String message() default "{org.terasoluna.gfw.common.codepoints.ConsistOf.aaa.message}";

    /**
     * Constraint groups
     *
     * @return constraint groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload
     *
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several <code>@ConsistOf</code> annotations on the same element
     *
     * @see ConsistOf
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER,
            TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        /**
         * <code>@ConsistOf</code> annotations
         *
         * @return annotations
         */
        ConsistOf[] value();
    }

    class ConsistOfValidator implements
            ConstraintValidator<ConsistOf, CharSequence> {

        private final java.util.List<String> classNameList = new ArrayList<>();
        /**
         * Array of CodePoints to check
         */
        private CodePoints[] codePointsArray;

        /**
         * initialize to validate with {@link ConsistOf}
         *
         * @param consistOf {@link ConsistOf} annotation
         */
        @Override
        public void initialize(ConsistOf consistOf) {
            Class<? extends CodePoints>[] classes = consistOf.value();
            this.codePointsArray = new CodePoints[classes.length];
            for (int i = 0; i < classes.length; i++) {
                this.classNameList.add(classes[i].getName());
                this.codePointsArray[i] = CodePoints.of(classes[i]);
            }
        }

        /**
         * Validate whether all code points in the given string are included in any {@link CodePoints} class specified by
         * {@link ConsistOf#value()}
         *
         * @param value   the string to check
         * @param context validation context
         * @return {@code true} if all code points in the given string are included in any {@link CodePoints} class specified by
         * {@link ConsistOf#value()} or the given string is {@code null}. {@code false} otherwise.
         */
        @Override
        public boolean isValid(CharSequence value,
                               ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            String s = value.toString();

            context.disableDefaultConstraintViolation();
            for (String className : classNameList) {
                context.buildConstraintViolationWithTemplate("{org.terasoluna.gfw.common.codepoints.ConsistOf." + className + ".message}")
                        .addConstraintViolation();
            }

            return CodePoints.containsAllInAnyCodePoints(s, codePointsArray);
        }
    }
}
