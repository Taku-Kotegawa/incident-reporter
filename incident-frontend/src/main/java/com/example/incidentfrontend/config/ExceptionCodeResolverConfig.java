package com.example.incidentfrontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.terasoluna.gfw.common.exception.SimpleMappingExceptionCodeResolver;

import java.util.LinkedHashMap;

@Configuration
public class ExceptionCodeResolverConfig {

    @Bean
    SimpleMappingExceptionCodeResolver exceptionCodeResolver() {
        LinkedHashMap<String, String> exceptions = new LinkedHashMap<>() {{
            put("ResourceNotFoundException", "e.ex.fw.5001");
            put("HttpRequestMethodNotSupportedException", "e.ex.fw.6001");
            put("MediaTypeNotAcceptableException", "e.ex.fw.6002");
            put("HttpMediaTypeNotSupportedException", "e.ex.fw.6003");
            put("MethodArgumentNotValidException", "e.ex.fw.7001");
            put("BindException", "e.ex.fw.7002");
            put("JsonParseException", "e.ex.fw.7003");
            put("UnrecognizedPropertyException", "e.ex.fw.7004");
            put("JsonMappingException", "e.ex.fw.7005");
            put("TypeMismatchException", "e.ex.fw.7006");
            put("BusinessException", "e.ex.fw.8001");
            put("OptimisticLockingFailureException", "e.ex.fw.8002");
            put("PessimisticLockingFailureException", "e.ex.fw.8002");
            put("DataAccessException", "e.ex.fw.9002");
        }};

        SimpleMappingExceptionCodeResolver resolver = new SimpleMappingExceptionCodeResolver();
        resolver.setExceptionMappings(exceptions);
        resolver.setDefaultExceptionCode("e.ex.fw.9001");
        return resolver;
    }


}
