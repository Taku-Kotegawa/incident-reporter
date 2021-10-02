package com.example.incidentfrontend.base.application;

import com.example.incidentfrontend.config.OAuth2Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Keycloakに対する操作を提供するクラス。
 */
@Slf4j
@Service
public class KeycloakService {

    private final RestTemplate restTemplate;
    private final OAuth2TokenService oAuth2TokenService;
    private final OAuth2ClientProperties.Registration registration;
    private final OAuth2Properties oAuth2Properties;

    public KeycloakService(RestTemplate restTemplate,
                           OAuth2TokenService oAuth2TokenService,
                           OAuth2ClientProperties oAuth2ClientProperties,
                           OAuth2Properties oAuth2Properties) {
        this.restTemplate = restTemplate;
        this.oAuth2TokenService = oAuth2TokenService;
        this.registration = oAuth2ClientProperties.getRegistration().get(oAuth2Properties.getRegId());
        this.oAuth2Properties = oAuth2Properties;
    }

    /**
     * Keycloakからログアウトする。
     * https://www.keycloak.org/docs/latest/securing_apps/index.html#logout-endpoint
     */
    public void logout() {
        // POSTするリクエストパラメーターを作成
        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("client_id", registration.getClientId());
        formParams.add("client_secret", registration.getClientSecret());
        formParams.add("refresh_token", oAuth2TokenService.getRefreshTokenValue());
        // リクエストヘッダーを作成
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        // リクエストを作成
        RequestEntity<MultiValueMap<String, String>> requestEntity =
                new RequestEntity<>(formParams, httpHeaders, HttpMethod.POST,
                        URI.create(oAuth2Properties.getLogoutUri()));
        // POSTリクエスト送信（ログアウト実行）
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        // ログ出力
        log.info("{}", responseEntity.getStatusCode());
        // log.info("{}", responseEntity.getBody());
    }
}