package com.example.incidentfrontend.config;

import com.example.incidentfrontend.base.application.KeycloakService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

    private final KeycloakService keycloakService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().authenticated();
        http.oauth2Login().permitAll();
        http.logout()
                .addLogoutHandler(logoutFromAuthServer())
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

    }

    /**
     * 認可サーバーからログアウトするLogoutHandler
     */
    private LogoutHandler logoutFromAuthServer() {
        return (request, response, authentication) -> {
            keycloakService.logout();
        };
    }

}
