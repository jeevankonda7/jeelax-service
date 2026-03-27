package com.nerdcoder.productapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

  private static final long EXPIRATION_TIME = 1000 * 60 * 60;
  private static final String SECRET = "This is my key for authentication@13476253478#nerdcoder";
  private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());


  public String generateToken(final String username) {
    return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  private Claims extractClaims(final String token) {
    return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

  }

  public String extractUsername(final String token) {
    return extractClaims(token).getSubject();
  }

  public boolean validateToken(String username, UserDetails userDetails, String token) {
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

  }

  private boolean isTokenExpired(final String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }
}
