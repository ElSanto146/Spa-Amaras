package com.amaras.spa.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static  final String SECRET_KEY= "kGn6sH7vN3mrC5ZsYtY/fYVkNz9zWR+7TcW8fgklXW+2Xri8ZRmWDXYEzUkzEj4Z8+x7TOpmIwN1plcPiOhIwA==";

    public String getToken(UserDetails user){
        Map<String, Object> claims = Map.of("roles", user.getAuthorities());//agregado
        return createToken(claims, user);
    }

    //Metodo para obtener el token con claims adicionales
    private String createToken (Map<String, Object> claims, UserDetails user){

        //Retornamos el token. Construimos el objeto
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))//fecha que se crea el token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//fecha de expiración, 1 día
                .signWith(getKey(), SignatureAlgorithm.HS256)//firmamos el token
                .compact();//método que construye y serializa el token
    }

    //Llevar la key a base 64 para mandarla a la firma del token
    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        //Llamanos al método para que cree una nueva instancia de nuestra secretKey
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //Método para validar el token
    public boolean validateToken(String token, UserDetails userDetails) {
        //Obtenemos el username del token
        final String username = getUsernameFromToken(token);
        //Comparamos si el username del token es igual al username del userDetails y si el token ha expirado
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Método privado para obtener todos los claims del token
    private Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())//clave secreta para la firma
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Método para obtener un claim específico
    public <T> T getClaim(String token, Function<Claims, T> claimResolver){
        //Obtenemos todos los claims
        final Claims claims = getAllClaims(token);
        //Retornamos el claim específico
        return claimResolver.apply(claims);
    }

    //Método para obtener la fecha de expiración del token
    private Date getExpirationDateFromToken(String token) {
        //Obtenemos la fecha de expiración del token
        return getClaim(token, Claims::getExpiration);
    }

    //Método para validar si el token ha expirado
    private boolean isTokenExpired(String token) {
        //Obtenemos la fecha de expiración del token y la comparamos con la fecha actual
        return getExpirationDateFromToken(token).before(new Date());
    }

    // Extraer roles del token
    public List<GrantedAuthority> extractRoles(String token) {
        Claims claims = getAllClaims(token);

        // Aseguramos que los roles son una lista de mapas con "authority"
        List<?> roles = claims.get("roles", List.class);
        if (roles == null || roles.isEmpty()) {
            return List.of(); // Retornar una lista vacía si no hay roles
        }

        // Convertir roles a GrantedAuthority
        return roles.stream()
                .filter(Map.class::isInstance) // Verificar que cada elemento es un mapa
                .map(role -> ((Map<?, ?>) role).get("authority")) // Obtener el valor de "authority"
                .filter(Objects::nonNull) // Excluir valores nulos
                .map(Object::toString) // Convertir a String
                .map(SimpleGrantedAuthority::new) // Crear SimpleGrantedAuthority
                .collect(Collectors.toList());
    }
}
