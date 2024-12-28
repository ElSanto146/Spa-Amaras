package com.amaras.spa.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static  final String SECRET_KEY= "586O325633257865214F44526582F462152325H52325454452GFD54897JHKH24FGH62354";

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    //Método para obtener el token con claims adicionales
    private String getToken (Map<String, Object> extraClaims, UserDetails user){
        //Retornamos el token. Construimos el objeto
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))//fecha que se crea el token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))//fecha de expiración, 1 día
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
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
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

}
