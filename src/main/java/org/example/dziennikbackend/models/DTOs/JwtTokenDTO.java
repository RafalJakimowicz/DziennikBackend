package org.example.dziennikbackend.models.DTOs;

public class JwtTokenDTO {
    private final String token;
    public JwtTokenDTO(String token) {
        this.token = token;
    }
    public String getToken() {  return token; }
}
