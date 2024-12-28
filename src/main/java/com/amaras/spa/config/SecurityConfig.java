package com.amaras.spa.config;


import com.amaras.spa.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//Para el buen funcionamiento de PreAuthorize
@RequiredArgsConstructor
public class SecurityConfig {
    //Esta clase va a contener la cadena de filtros y el método securityFilterChain

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    //Crear la cadena de filtros. Primero diferenciar los endpoints públicos de los privados
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {
        //Retornar el http si pasa la cadena de filtros
        return http
                .csrf(csrf ->
                        csrf.disable())//Deshabilitamos los csrf, porque vamos a trabajar con nuestros jwt
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()//Endpoints públicos
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")//Endpoints para el admin
                                .requestMatchers("/api/v1/user/**").hasAnyRole("USER", "ADMIN")//Endpoints para el user y el admin
                                .anyRequest().authenticated()
                                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))//Desactivamos las sesiones
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
