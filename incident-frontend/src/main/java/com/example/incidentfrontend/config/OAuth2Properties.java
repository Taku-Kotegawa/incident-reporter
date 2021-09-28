package com.example.incidentfrontend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2Properties {

    private String issuerUri;
    private String authServerUri;
    private String resourceServerUri;
    private String logoutUri;

}
