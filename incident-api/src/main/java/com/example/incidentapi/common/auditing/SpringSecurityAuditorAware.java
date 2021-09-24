package com.example.incidentapi.common.auditing;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

/**
 * Spring Data JPA Audit 機能を実装するクラス.
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    /**
     * @return {@code @CreatedBy, @LastModifiedBy} にセットするログインユーザ名。
     * ログインしていない場合は"unknown"を返す。
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<String> getCurrentAuditor() {

        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.of(user.getUsername());

        } catch (Exception e) {
            return Optional.of("unknown");

        }
    }
}