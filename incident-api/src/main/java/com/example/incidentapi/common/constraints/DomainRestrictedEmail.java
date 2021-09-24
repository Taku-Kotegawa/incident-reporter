/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.incidentapi.common.constraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {DomainRestrictedEmail.DomainRestrictedEmailValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@Repeatable(DomainRestrictedEmail.List.class)
@Email
public @interface DomainRestrictedEmail {
    String message() default "{com.example.app.common.validation.DomainRestrictedEmail.message}";

    Class<?>[] groups() default {};

    String[] allowedDomains() default {};

    boolean allowSubDomain() default false;

    Class<? extends Payload>[] payload() default {};

    @Target(FIELD)
    @Retention(RUNTIME)
    @Documented
    @interface List {
        DomainRestrictedEmail[] value();
    }

    class DomainRestrictedEmailValidator implements
            ConstraintValidator<DomainRestrictedEmail, CharSequence> {

        private Set<String> allowedDomains;

        private boolean allowSubDomain;

        @Override
        public void initialize(DomainRestrictedEmail constraintAnnotation) {
            allowedDomains = new HashSet<>(Arrays.asList(constraintAnnotation
                    .allowedDomains()));
            allowSubDomain = constraintAnnotation.allowSubDomain();
        }

        @Override
        public boolean isValid(CharSequence value,
                               ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }

            for (String domain : allowedDomains) {
                if (value.toString().endsWith("@" + domain)
                        || (allowSubDomain && value.toString().endsWith(
                        "." + domain))) {
                    return true;
                }
            }
            return false;
        }

    }

}
