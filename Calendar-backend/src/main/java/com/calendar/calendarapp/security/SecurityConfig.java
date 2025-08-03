package com.calendar.calendarapp.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configure HTTP Security
    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/api/users/register", "/api/events/**").permitAll()  // Allow public access to certain endpoints
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());  // Enable HTTP Basic authentication (can be replaced with JWT later)

        // If you're sure about disabling CSRF protection (e.g., for REST APIs):
        http.csrf(csrf -> csrf.disable()); // Deprecated, but still functional in Spring Security 6.x

        return http.build();
    }

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
