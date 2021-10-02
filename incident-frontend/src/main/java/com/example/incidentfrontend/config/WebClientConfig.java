package com.example.incidentfrontend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    private static final int WEB_API_TIMEOUT_MS = 30000;

    /**
     * WebClient初期設定
     *
     * @param clientRegistrations
     * @param authorizedClients
     * @return
     * @see https://spring.pleiades.io/spring-security/site/docs/current/reference/html5/#webclient-setup
     */
    @Bean
    WebClient webClient(ClientRegistrationRepository clientRegistrations,
                        OAuth2AuthorizedClientRepository authorizedClients,
                        OAuth2Properties oAuth2Properties) {

        // OAuth2アクセストークン自動設定
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);
        // (optional) explicitly opt into using the oauth2Login to provide an access token implicitly
        oauth.setDefaultOAuth2AuthorizedClient(true);
        // (optional) set a default ClientRegistration.registrationId
        // oauth.setDefaultClientRegistrationId("client-registration-id");

        // タイムアウト設定
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .doOnConnected(conn -> conn
                .addHandlerLast(new ReadTimeoutHandler(1000))
                .addHandlerLast(new WriteTimeoutHandler(1000)));

        return WebClient.builder()
                .baseUrl(oAuth2Properties.getResourceServerUri())
                .filter(oauth)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
