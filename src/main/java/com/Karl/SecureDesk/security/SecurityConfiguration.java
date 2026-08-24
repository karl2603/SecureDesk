package com.Karl.SecureDesk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    //Authentication Provider, DaoAuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/SecureDesk/home",
                                "/SecureDesk/users/register"
                        ).permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )

                .authenticationProvider(authenticationProvider)

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}


