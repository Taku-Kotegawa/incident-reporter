package com.example.incidentfrontend.incident.presentation.controller.incident;

import com.example.incidentfrontend.config.OAuth2Properties;
import com.example.incidentfrontend.incident.presentation.dto.IncidentResource;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("incident")
@AllArgsConstructor
public class IncidentController {

    private final WebClient webClient;
    private final OAuth2Properties oauth2Properties;


    @GetMapping("")
    public String top() {
        return "redirect:/incident/list";
    }


    @GetMapping("list")
    public String list(Model model, @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {

        List<IncidentResource> incidents =
                webClient.get()
                        .uri("/api/incidents")
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<IncidentResource>>() {})
                .block();

        model.addAttribute("incidentList", incidents);
        return "incident/list";
    }

}
