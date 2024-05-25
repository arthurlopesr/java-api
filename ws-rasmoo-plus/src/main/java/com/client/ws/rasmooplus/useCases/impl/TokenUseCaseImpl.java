package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.useCases.TokenUseCase;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenUseCaseImpl implements TokenUseCase {
    @Value("${webservices.rasplus.jwt.expiration}")
    private String expiration;

    @Value("${webservices.rasplus.jwt.secret}")
    private String secret;

    @Override
    public String getToken(Long userId) {

        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setIssuer("API Rasmoo Plus")
                .setSubject(userId.toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Boolean isValid(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getUserId(String token) {
        Jws<Claims> claims = getClaimsJws(token);
        return Long.parseLong(claims.getBody().getSubject());
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token);
    }
}
