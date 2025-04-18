package org.example.dziennikbackend.configs;

import org.example.dziennikbackend.models.DTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil{
    private final String TOKEN_KEY = "hv56X@GN_?EFP%TO^*+=d*Lgoj673D-l";

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

    public String generateToken(UserDTO user, int expirationHours){
        return Jwts.builder().setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*expirationHours))
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
    }

    public Boolean validateToken(String token, UserDTO user) {
        final String username = this.extractUsernameFromToken(token);
        return (username.equals(user.getLogin()) && !isTokenExpired(token));
    }

}
