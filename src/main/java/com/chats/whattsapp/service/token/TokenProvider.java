package com.chats.whattsapp.service.token;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.chats.whattsapp.config.JwtConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider {

    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.getSecretKey().getBytes());

    public String generateToken(Authentication authentication) {

        String jwt = Jwts.builder()
            .setIssuer("Sergioqfeg")
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + 864000000))
            .claim("email", authentication.getName())
            .signWith(key)
            .compact();

        return jwt;
    }

    public String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claim = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claim.get("email"));
        return email;
    }

}
