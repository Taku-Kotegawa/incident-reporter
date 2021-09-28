package com.example.incidentfrontend.incident.presentation.controller.incident;

import com.example.incidentfrontend.base.application.KeycloakService;
import com.example.incidentfrontend.base.application.OAuth2TokenService;
import com.example.incidentfrontend.config.OAuth2Properties;
import com.example.incidentfrontend.incident.common.WebClientSample;
import com.example.incidentfrontend.incident.presentation.dto.IncidentResource;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("incident")
@AllArgsConstructor
public class IncidentController {

    private final OAuth2TokenService oAuth2TokenService;
    private final RestTemplate restTemplate;
    private final KeycloakService keycloakService;
    private final WebClientSample webClientSample;
    private final OAuth2Properties oauth2Properties;


    @GetMapping("")
    public String top() {
        return "redirect:/incident/list";
    }


    @GetMapping("list")
    public String list(Model model, @AuthenticationPrincipal Authentication auth) {

        String accessToken = oAuth2TokenService.getAccessTokenValue();
        String uri = oauth2Properties.getResourceServerUri() + "/api/incidents";

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
