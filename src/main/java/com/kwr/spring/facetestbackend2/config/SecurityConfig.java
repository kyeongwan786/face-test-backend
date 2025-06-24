// 📁 com.kwr.spring.facetestbackend2.config.SecurityConfig
package com.kwr.spring.facetestbackend2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (프론트에서 호출 시)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // ✅ API 전체 허용
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()); // 또는 formLogin().disable() 추가해도 됨
        return http.build();
    }
}
