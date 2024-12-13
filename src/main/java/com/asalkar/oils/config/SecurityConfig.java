package com.asalkar.oils.config;

//import javax.servlet.http.HttpServletRequest;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.asalkar.dto.filter.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
     
	private final JwtFilter jwtFilter;

    // Constructor Injection for JwtFilter
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) 
                
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(new AntPathRequestMatcher("/api/cart/update"),
                                		new AntPathRequestMatcher("/api/cart"),
                                		new AntPathRequestMatcher("/api/cart/add"),
                                		new AntPathRequestMatcher("/api/login"), 
                                		new AntPathRequestMatcher("/api/registeruser"), 
                                		new AntPathRequestMatcher("/products"),
                                		new AntPathRequestMatcher("/updateStock/{variantId}"),
                                		new AntPathRequestMatcher("/checkStock/{variantId}"),
                                		new AntPathRequestMatcher("/productvariants/{productid}")).permitAll() // Public endpoints
                                .anyRequest().authenticated() // All other endpoints require authentication
                )
                .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session for JWT
                )
                .httpBasic(withDefaults()); // Optional: Basic Auth for simplicity during testing

        // Add JWT filter (if required for additional validation)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
