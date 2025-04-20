package org.example.dziennikbackend.configs;

import jakarta.persistence.SecondaryTable;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class JwtUtil{
    private final String TOKEN_KEY = "hv56X@GN_?EFP%TO^*+=d*Lgoj673D-l";

    //in memory blacklisted tokens
    private final Set<String> revokedJti = ConcurrentHashMap.newKeySet();

    public <T> T extractDataFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String extractUsernameFromToken(String token) {
        return extractDataFromToken(token, Claims::getSubject);
    }

    public Date extractExpirationDateFromToken(String token) {
        return extractDataFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(AuthDTO user, int expirationHours){
        String jti = UUID.randomUUID().toString();
        return Jwts.builder().setId(jti)
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*expirationHours))
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
    }

    public Boolean validateToken(String token, AuthDTO user) {
        final String username = this.extractUsernameFromToken(token);
        return (username.equals(user.getLogin()) && !isTokenExpired(token) && !isTokenRevoked(token));
    }

    private Boolean isTokenRevoked(String token) {
        String jti = this.extractDataFromToken(token, Claims::getId);
        return revokedJti.contains(jti);
    }

    public void revokeToken(String token) {
        String jti = this.extractDataFromToken(token, Claims::getId);
        revokedJti.add(jti);
    }

}
