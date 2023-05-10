package org.example.buisness.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.buisness.AccessTokenDecoder;
import org.example.buisness.AccessTokenEncoder;
import org.example.buisness.Exceptions.InvalidAccessTokenException;
import org.example.domain.AccessToken;
import org.example.domain.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(accessToken.getRoles())) {
            claimsMap.put("roles", accessToken.getRoles());
        }
        if (accessToken.getUserId() != null) {
            claimsMap.put("userId", accessToken.getUserId());
        }



        Instant now = Instant.now();



        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    public String encodeRefreshToken(RefreshToken refreshToken) {
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(refreshToken.getSubject())
                .setId(Long.toString(refreshToken.getUserId()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS))) // Set a longer expiration for refresh tokens
                .signWith(key)
                .compact();
    }
    public RefreshToken decodeRefreshToken(String refreshTokenEncoded) {
        try {
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(refreshTokenEncoded);
            Claims claims = (Claims) jwt.getBody();

            return RefreshToken.builder()
                    .subject(claims.getSubject())
                    .userId(Long.parseLong(claims.getId()))
                    .build();
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }





    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(accessTokenEncoded);
            Claims claims = (Claims) jwt.getBody();

            List<String> role = claims.get("roles", List.class);

            return AccessToken.builder()
                    .subject(claims.getSubject())
                    .roles(role)
                    .userId(claims.get("userId", Long.class))
                    .build();
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
