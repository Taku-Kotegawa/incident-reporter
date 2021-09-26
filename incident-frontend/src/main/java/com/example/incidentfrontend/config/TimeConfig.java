package com.example.incidentfrontend.config;



import com.example.incidentfrontend.common.datetime.CustomDateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.terasoluna.gfw.common.date.jodatime.DefaultJodaTimeDateFactory;

@Configuration
public class TimeConfig {

    @Bean
    public DefaultJodaTimeDateFactory defaultJodaTimeDateFactory() {
        return new DefaultJodaTimeDateFactory();
    }

    @Bean
    public CustomDateFactory customDateFactory() {
        return new CustomDateFactory();
    }

}
