// 📁 com.kwr.spring.facetestbackend2.config.SecurityConfig
package com.kwr.spring.facetestbackend2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {}) // ✅ 반드시 먼저 cors() 활성화
                .csrf(csrf -> csrf.disable()) // ❗️Spring Security 6 기준
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/comments/**").permitAll()
                        .anyRequest().permitAll() // 필요시 제한
                );
        return http.build();
    }
}
