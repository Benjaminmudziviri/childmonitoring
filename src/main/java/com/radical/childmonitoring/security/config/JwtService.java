package com.radical.childmonitoring.security.config;



import com.radical.childmonitoring.security.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {

    @Value("${application.jwt.secret-key}")
    private String secretKey;

    @Value("${application.jwt.expiration}")
    private long accessTokenExpirationMs; // Expiration time in milliseconds for the access token

    @Value("${application.jwt.refresh-token.expiration}")
    private long refreshTokenExpirationMs; // Expiration time in milliseconds for the refresh token


    // 🔑 Key for signing JWTs
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // ✅ Generate Access Token (short-lived)
    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());  // include role claim
        return generateToken(claims, user, accessTokenExpirationMs);
    }

    // ✅ Generate Refresh Token (long-lived, includes deviceId)
    public String generateRefreshToken(User user, String deviceId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());       // still include role
        claims.put("deviceId", deviceId);                // 🔐 include deviceId
        return generateToken(claims, user, refreshTokenExpirationMs);
    }

    // 🔁 Core token generator
    private String generateToken(Map<String, Object> extraClaims, User user, long expirationMs) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())  // This is used as the unique identifier
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🧠 Get username (subject) from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 🧠 Get deviceId (for refresh token validation)
    public String extractDeviceId(String token) {
        return extractClaim(token, claims -> claims.get("deviceId", String.class));
    }

    // 🧠 Get role from token if needed
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // ✅ Generic claim extractor
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // ✅ Token validity checker
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    // ✅ Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 🔍 Get all claims from JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

