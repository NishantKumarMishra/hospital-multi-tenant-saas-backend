package com.medi_connect.Medi_Connect.config;

import com.medi_connect.Medi_Connect.Utils.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("SECURITY CONFIG LOADED");
        http
                // 🔴 Disable CSRF (API-based auth)
                .csrf(csrf -> csrf.disable())

                // 🔥 Enable CORS
                .cors(Customizer.withDefaults())

                // 🔥 Stateless (JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // 🔥 Allow preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 🔓 Public APIs
                        .requestMatchers(
                                "/auth/**",       // login, otp, register
                                "/public/**"
                        ).permitAll()

                        // 🔐 Admin only
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN","STAFF")
                        .requestMatchers("/staff/**").hasAuthority("STAFF")

                        // 🔐 Doctor only
                        .requestMatchers("/doctor/**").hasAuthority("DOCTOR")

                        // 🔐 Everything else needs auth
                        .anyRequest().authenticated()
                )

                // ❌ Disable basic auth
                .httpBasic(httpBasic -> httpBasic.disable());

        // 🔥 JWT FILTER
        http.addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
