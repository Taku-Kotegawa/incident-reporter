package com.example.incidentapi.incident.domain.model;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class IncidentAuthority {

    @PostAuthorize("returnObject == true")
    public Boolean hasAuthority(String operation) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return true;
    }

}
