package org.example.dziennikbackend.configs;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.dziennikbackend.models.DTOs.AuthDTO;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class JwtUtil {

    /* ---------- helpers -------------------------------------------------- */

    private SecretKey getSigningKey(){
        String TOKEN_KEY_B64;
        try{
            ConfigService cs = new ConfigService();
            TOKEN_KEY_B64 = cs.getConfigObject().getTokenPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // ⚠️  For HS256 the *raw* key must be ≥ 256 bits (32 bytes). Replace this with a stronger value in prod.
        // demo only (“haslo123”)
        byte[] keyBytes = Decoders.BASE64.decode(TOKEN_KEY_B64);   // use jjwt-provided Decoders
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private final Set<String> revokedJti = ConcurrentHashMap.newKeySet();

    /* ---------- claim extraction ----------------------------------------- */

    public <T> T extractDataFromToken(String token, Function<Claims,T> resolver) {
        Claims claims = Jwts.parserBuilder()                       // new API
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return resolver.apply(claims);
    }

    public String extractUsernameFromToken(String token) {
        return extractDataFromToken(token, Claims::getSubject);
    }

    public Date extractExpirationDateFromToken(String token) {
        return extractDataFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDateFromToken(token).before(new Date());
    }

    /* ---------- generation ------------------------------------------------ */

    public String generateToken(AuthDTO user, int expirationHours) {
        String jti   = UUID.randomUUID().toString();
        Date   now   = new Date();
        Date   until = new Date(now.getTime() + expirationHours * 3_600_000L);

        return Jwts.builder()
                .setId(jti)
                .setSubject(user.getLogin())
                .setIssuedAt(now)
                .setExpiration(until)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)   // <-- key first, alg second
                .compact();
    }

    /* ---------- validation / revocation ---------------------------------- */

    public boolean validateToken(String token, AuthDTO user) {
        String username = extractUsernameFromToken(token);
        return username.equals(user.getLogin())
                && !isTokenExpired(token)
                && !isTokenRevoked(token);
    }

    private boolean isTokenRevoked(String token) {
        String jti = extractDataFromToken(token, Claims::getId);
        return revokedJti.contains(jti);
    }

    public void revokeToken(String token) {
        String jti = extractDataFromToken(token, Claims::getId);
        revokedJti.add(jti);
    }
}
