package com.guilherme.projectportfolioapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                throws Exception {

                http
                        .csrf(csrf -> csrf.disable())
                        .cors(cors -> {})
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/projetos/**")
                                .permitAll()
                                .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());

                return http.build();
        }
}