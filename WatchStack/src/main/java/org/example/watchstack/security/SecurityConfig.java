package org.example.watchstack.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder pw) {
        UserDetails user = User.builder()
                .username("user")
                .password(pw.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(pw.encode("verysecretpassword"))
                .roles("ADMINISTRATOR")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Swagger UI and API docs public
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // User management only ADMINISTRATOR
                        .requestMatchers("/api/users/**").hasRole("ADMINISTRATOR")

                        // Movies: GET by USER or ADMINISTRATOR; modifications only ADMINISTRATOR
                        .requestMatchers(HttpMethod.GET, "/api/movies/**").hasAnyRole("USER", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/api/movies/**").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/movies/**").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/movies/**").hasRole("ADMINISTRATOR")

                        // Custom lists: CRUD by USER or ADMINISTRATOR
                        .requestMatchers("/api/custom-lists/**").hasAnyRole("USER", "ADMINISTRATOR")

                        // List items: CRUD by USER or ADMINISTRATOR
                        .requestMatchers("/api/list-items/**").hasAnyRole("USER", "ADMINISTRATOR")

                        // Watchlist items: CRUD by USER or ADMINISTRATOR
                        .requestMatchers("/api/watchlist-items/**").hasAnyRole("USER", "ADMINISTRATOR")

                        // Watched entries: CRUD by USER or ADMINISTRATOR
                        .requestMatchers("/api/watched-entries/**").hasAnyRole("USER", "ADMINISTRATOR")

                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")))
                .addSecurityItem(new SecurityRequirement().addList("basicScheme"));
    }
}
