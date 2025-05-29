package dev.al.PWork.config;

import dev.al.PWork.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsServiceImpl userDetailsService;

    public SecurityConfig(CustomUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // për testim me Postman

                .authorizeHttpRequests(auth -> auth
                        // Vetem RECRUITER mund te krijoje, fshije dhe ndryshoje user-at
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("RECRUITER")

                        // JOB_SEEKER dhe RECRUITER mund te shohin user-at (GET)
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("RECRUITER", "JOB_SEEKER")




                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
