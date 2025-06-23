package com.kwr.spring.facetestbackend2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/comments/**").permitAll()
                        .anyRequest().permitAll()
                )
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());  // 최신 문법에서의 disable 방식

        return http.build();
    }
}
