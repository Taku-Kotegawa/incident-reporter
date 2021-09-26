package com.example.incidentapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.models.security.OAuthFlow;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "障害報告メール送信アプリのAPI",  // (1)
                description = "これはAPI-First開発のトライアルプロジェクト「障害報告メール送信アプリ」のためのAPIを提供します。<br> ２行目 <br> ３行目",  // (2)
                version = "v1",
                termsOfService = "https://www.pikara.jp/terms/"
                , license = @License(name = "MIT License", url = "https://licenses.opensource.jp/MIT/MIT.html")
                , contact = @Contact(name = "株式会社STNet 情報システム本部ソリューション統括部", email = "xxxx@stnet.co.jp", url = "http://www.stnet.co.jp")),
        servers = @Server(url = "http://localhost:8080/", description = "テスト用", variables = @ServerVariable(name = "api-key", defaultValue = "secret")),
        security = @SecurityRequirement(name = "BASIC")
)
public class SpringdocOpenApiConfig {
}
