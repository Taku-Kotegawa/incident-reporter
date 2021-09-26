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
package com.example.incidentfrontend.common.constraints;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = UploadFileMaxSize.UploadFileMaxSizeValidator.class)
@Repeatable(UploadFileMaxSize.List.class)
public @interface UploadFileMaxSize {
    String message() default "{com.example.app.common.validation.UploadFileMaxSize.message}";

    long value() default (1024 * 1024);

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER,
            TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        UploadFileMaxSize[] value();
    }

    class UploadFileMaxSizeValidator implements
            ConstraintValidator<UploadFileMaxSize, MultipartFile> {

        private UploadFileMaxSize constraint;

        @Override
        public void initialize(UploadFileMaxSize constraint) {
            this.constraint = constraint;
        }

        @Override
        public boolean isValid(MultipartFile multipartFile,
                               ConstraintValidatorContext context) {
            if (constraint.value() < 0 || multipartFile == null) {
                return true;
            }
            return multipartFile.getSize() <= constraint.value();
        }

    }

}