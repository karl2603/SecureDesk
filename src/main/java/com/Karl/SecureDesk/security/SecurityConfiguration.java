package com.Karl.SecureDesk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtFilter jwtFilter;

    //Authentication Provider, DaoAuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    //Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig){
        return authConfig.getAuthenticationManager();
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Public endpoints, it can be accessed by all
                        .requestMatchers(
                                "/SecureDesk/home",
                                "/SecureDesk/users/register",
                                "/SecureDesk/login"
                        ).permitAll()

                        // Admin-only endpoints, can only be accessed by admin
                        .requestMatchers(
                                "/SecureDesk/tickets",
                                "/SecureDesk/tickets/active",
                                "/SecureDesk/ticket/*/status"
                        ).hasRole("ADMIN")

                        // Everything else — user and admin can both access it
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )

                .authenticationProvider(authenticationProvider)

                //JWT Token Validation
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}


