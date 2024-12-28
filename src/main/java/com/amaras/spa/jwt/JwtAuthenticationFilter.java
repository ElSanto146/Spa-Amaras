package com.amaras.spa.jwt;

import com.amaras.spa.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
//La clase abstracta OncePerRequestFilter nos permite crear filtros personalizados
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inyección de dependencias
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos el token del header y el username
        final String token = getTokenFromRequest(request);
        final String username;

        //Validamos si el token es nulo
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        //Si el token es correcto acceder al username
        username = jwtService.getUsernameFromToken(token);

        //Si username no es nulo y está autenticado. y no está en el securityContextHolder lo buscamos en la bbdd
        if (username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //Validamos si el token es correcto
            if (jwtService.validateToken(token, userDetails)){

                List<GrantedAuthority> authorities = jwtService.extractRoles(token);

                //Si es correcto se autentica
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, authorities);
                //Se añade los detalles de la petición
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Se autentica
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        //llamamos al filtro para que la app siga su curso
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //Validar si existe este texto en el encabezado y si el authHeader comienza con "Bearer"
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            //retornamos desde la posición 7 para omitir la palabra Bearer. Que es donde está el token
            return authHeader.substring(7);
        }
        return null;
    }
        //minuto22:54
}
