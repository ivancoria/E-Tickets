package com.ivancoria.etickets.services;

import com.ivancoria.etickets.entities.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

     @Value("${jwt.secret-key}")
     private String secretKey;
     @Value("${jwt.expiration}")
     private Long jwtExpiration;

     /* Se extrae el email en este caso */
     public String extractUsername(String token) {
         return extractClaim(token, Claims::getSubject);
     }

     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
          final Claims claims = extractAllClaims(token);
          return claimsResolver.apply(claims);
     }

     private Claims extractAllClaims(String token) {
          return Jwts
                  .parser()
                  .setSigningKey(getSingInKey())
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
     }

     public boolean isTokenValid (String token, UserDetails userDetails) {
          final String username = extractUsername(token);
          return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
     }

     public boolean isTokenExpired (String token) {
          return extractExpiration(token).before(new Date());
     }

     public Date extractExpiration (String token) {
          final Claims jwtToken = Jwts.parser()
                  .verifyWith(getSingInKey())
                  .build()
                  .parseSignedClaims(token)
                  .getPayload();
          return jwtToken.getExpiration();
     }

     public String generateToken(UserEntity user) {
          return buildToken(user, jwtExpiration);
     }

     public String buildToken(final UserEntity user, final long jwtExpiration) {
          return Jwts.builder()
                  .id(user.getId().toString())
                  .claims(Map.of("role", user.getRole()))
                  .subject(user.getEmail())
                  .issuedAt(new Date(System.currentTimeMillis()))
                  .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                  .signWith(getSingInKey())
                  .compact();
     }

     public SecretKey getSingInKey() {
          byte[] keyBytes = Decoders.BASE64.decode(secretKey);
          return Keys.hmacShaKeyFor(keyBytes);
     }
}
