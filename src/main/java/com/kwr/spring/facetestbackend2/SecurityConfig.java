// 📁 com.kwr.spring.facetestbackend2.config.SecurityConfig
package com.kwr.spring.facetestbackend2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // CORS 설정 연결
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/comments/**").permitAll()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}

