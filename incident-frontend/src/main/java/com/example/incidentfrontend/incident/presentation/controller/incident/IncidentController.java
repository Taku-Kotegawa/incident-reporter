package com.example.incidentfrontend.incident.presentation.controller.incident;

import com.example.incidentfrontend.incident.application.service.KeycloakService;
import com.example.incidentfrontend.incident.application.service.OAuth2TokenService;
import com.example.incidentfrontend.incident.presentation.dto.IncidentResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("incident")
public class IncidentController {

    private final OAuth2TokenService oAuth2TokenService;
    private final RestTemplate restTemplate;
    private final KeycloakService keycloakService;
    @Value("${resource-server.base-uri}")
    private String resourceServerBaseUri;

    public IncidentController(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService, RestTemplate restTemplate, OAuth2TokenService oAuth2TokenService, KeycloakService keycloakService) {
        this.oAuth2TokenService = oAuth2TokenService;
        this.restTemplate = restTemplate;
        this.keycloakService = keycloakService;
    }

    @GetMapping("")
    public String top() {
        return "redirect:/incident/list";
    }


    @GetMapping("list")
    public String list(Model model, @AuthenticationPrincipal Authentication auth) {


        String accessToken = oAuth2TokenService.getAccessTokenValue();
        String uri = resourceServerBaseUri + "/api/incidents";


        RequestEntity requestEntity = RequestEntity
                .get(uri)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        ResponseEntity<List<IncidentResource>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<IncidentResource>>(){});

        model.addAttribute("incidentList", responseEntity.getBody());


        return "incident/list";

    }


}
