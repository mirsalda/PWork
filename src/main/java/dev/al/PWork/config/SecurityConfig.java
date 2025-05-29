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
                // Çaktivizojme CSRF per testim me Postman (nuk eshte e rekomanduar ne prodhim)
                .csrf(csrf -> csrf.disable())

                // Konfigurimi i rregullave per akses ne endpoint-et e API
                .authorizeHttpRequests(auth -> auth
                        // Vetem RECRUITER mund te beje Krijim, Ndryshim dhe Fshirje (CRUD) per users
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("RECRUITER")

                        // GET per users lejohet per RECRUITER dhe JOB_SEEKER
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("RECRUITER", "JOB_SEEKER")

                        // CRUD per recruiters lejohet vetem per RECRUITER
                        .requestMatchers(HttpMethod.POST, "/api/recruiters/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.PUT, "/api/recruiters/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.DELETE, "/api/recruiters/**").hasRole("RECRUITER")

                        // Vetem RECRUITER mund te shikoje recruiters me GET
                        .requestMatchers(HttpMethod.GET, "/api/recruiters/**").hasRole("RECRUITER")

                        // CRUD per jobseekers lejohet vetem per RECRUITER
                        .requestMatchers(HttpMethod.POST, "/api/jobseekers/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.PUT, "/api/jobseekers/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.DELETE, "/api/jobseekers/**").hasRole("RECRUITER")

                        // GET per jobseekers mund te beje RECRUITER dhe JOB_SEEKER
                        .requestMatchers(HttpMethod.GET, "/api/jobseekers/**").hasAnyRole("RECRUITER", "JOB_SEEKER")

                        // Cdo kerkese tjeter lejohet per te gjithe pa autentikim
                        .anyRequest().permitAll()
                )
                // Aktivizojme Basic HTTP Authentication me konfigurim default
                .httpBasic(withDefaults());

        return http.build();
    }

    // Bean qe siguron perdorimin e BCrypt per enkriptimin e passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean per menaxhimin e autentikimit (p.sh. per Login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
