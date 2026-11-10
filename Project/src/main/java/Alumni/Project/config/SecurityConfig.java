package Alumni.Project.config;

import Alumni.Project.security.JwtAuthenticationFilter;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())

                .formLogin(form -> form.disable())

                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // SPRING ERROR DISPATCH
                        // =========================

                        .dispatcherTypeMatchers(
                                DispatcherType.ERROR
                        ).permitAll()


                        // =========================
                        // AUTHENTICATION
                        // =========================

                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login"
                        ).permitAll()


                                // =========================
// SWAGGER / OPENAPI
// =========================
                                .requestMatchers(
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()

                        // =========================
                        // ADMIN
                        // =========================

                        .requestMatchers(
                                "/api/admins/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // USER MANAGEMENT
                        // =========================

                        // User management is ADMIN only.
                        // Public registration is handled by
                        // /api/auth/register above.


                        .requestMatchers(
                                "/api/users/me",
                                "/api/users/me/password"
                        ).authenticated()

                        .requestMatchers(
                                "/api/users/**"
                        ).hasRole("ADMIN")



                        // =========================
                        // ALUMNI
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/alumni"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/alumni/*/skills/*"
                        ).authenticated()

                                // Employment history: ownership checked in the controller
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/alumni/*/employment-history/*"
                                ).hasAnyRole("ALUMNI", "ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/alumni/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/api/alumni/**"
                        ).authenticated()


                        // =========================
                        // SKILLS
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/skills"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/skills/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/skills/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/skills/**"
                        ).authenticated()


                        // =========================
                        // DEPARTMENTS
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/departments"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/departments/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/departments/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/departments/**"
                        ).authenticated()


                        // =========================
                        // JOB OPPORTUNITIES
                        // =========================

                        // ADMIN and EMPLOYER can create jobs
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/job-opportunities"
                        ).hasAnyRole(
                                "ADMIN",
                                "EMPLOYER"
                        )

                        // Currently only ADMIN can update jobs
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/job-opportunities/**"
                        ).hasRole("ADMIN")

                        // Currently only ADMIN can delete jobs
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/job-opportunities/**"
                        ).hasRole("ADMIN")

                        // Authenticated users can view jobs
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/job-opportunities/**"
                        ).authenticated()


                        // =========================
                        // EVENTS
                        // =========================

                        // Only ADMIN can create events
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/events"
                        ).hasRole("ADMIN")

                        // Only ADMIN can update events
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/events/**"
                        ).hasRole("ADMIN")

                        // Alumni can register themselves.
                        // The service checks ownership.
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/events/*/register/*"
                        ).authenticated()

                        // Authenticated users can reach participant
                        // removal. The service checks ownership.
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/events/*/participants/*"
                        ).authenticated()

                        // Only ADMIN can delete an entire event
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/events/**"
                        ).hasRole("ADMIN")

                        // Authenticated users can view events
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/events/**"
                        ).authenticated()


                        // =========================
                        // ANNOUNCEMENTS
                        // =========================

                        // Authenticated users can view announcements
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/announcements/**"
                        ).authenticated()

                        // Only ADMIN can create announcements
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/announcements"
                        ).hasRole("ADMIN")

                        // Only ADMIN can update announcements
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/announcements/**"
                        ).hasRole("ADMIN")

                        // Only ADMIN can delete announcements
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/announcements/**"
                        ).hasRole("ADMIN")




                                // =========================
// JOB SKILLS
// =========================

// Only ADMIN can add required skills to jobs
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/job-skills"
                                ).hasRole("ADMIN")

// Only ADMIN can change required-skill status
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/api/job-skills/**"
                                ).hasRole("ADMIN")

// Only ADMIN can remove required skills from jobs
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/job-skills/**"
                                ).hasRole("ADMIN")

// Authenticated users can view job skills
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/job-skills/**"
                                ).authenticated()


                        // =========================
                        // RECOMMENDATIONS
                        // =========================

                        // TEMPORARY:
                        // Recommendation security will be reviewed
                        // separately.
                                // =========================
// RECOMMENDATIONS
// =========================

// Authenticated users can access job recommendations
                                .requestMatchers(
                                        "/api/recommendations/**"
                                ).authenticated()


                        // =========================
                        // ERROR
                        // =========================

                        .requestMatchers(
                                "/error"
                        ).permitAll()


                        // =========================
                        // EVERYTHING ELSE
                        // =========================

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                java.util.List.of(
                        "http://127.0.0.1:5500",
                        "http://localhost:5500",
                        "http://localhost:63342"
                )
        );

        configuration.setAllowedMethods(
                java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
                java.util.List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}