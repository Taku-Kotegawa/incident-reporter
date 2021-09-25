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

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.io.File;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {FileNamePattern.FileNamePatternValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@Repeatable(FileNamePattern.List.class)
public @interface FileNamePattern {

    String message() default "{com.example.app.common.validation.FileNamePattern.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String pattern() default "";

    @Target(FIELD)
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FileNamePattern[] value();
    }

    class FileNamePatternValidator implements
            ConstraintValidator<FileNamePattern, MultipartFile> {

        private Pattern pattern;

        @Override
        public void initialize(FileNamePattern constraintAnnotation) {
            this.pattern = Pattern.compile(constraintAnnotation.pattern());
        }

        @Override
        public boolean isValid(MultipartFile value,
                               ConstraintValidatorContext context) {
            if (value == null || value.getOriginalFilename() == null) {
                return true;
            }

            String filename = new File(value.getOriginalFilename()).getName();
            return pattern.matcher(filename).matches();
        }

    }

}