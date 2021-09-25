package com.example.incidentapi.config;


import com.example.incidentapi.common.auditing.SpringSecurityAuditorAware;
import com.example.incidentapi.common.datetime.DateTimeProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware", dateTimeProviderRef = "dateTimeProviderImpl")
public class JpaConfig {

    @Bean(name = "springSecurityAuditorAware")
    public SpringSecurityAuditorAware springSecurityAuditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Bean
    public DateTimeProviderImpl dateTimeProviderImpl() {
        return new DateTimeProviderImpl();
    }

}
