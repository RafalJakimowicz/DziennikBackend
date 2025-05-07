package org.example.dziennikbackend.models.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    private String login;
    private String password;

    public AuthDTO() {}
    public AuthDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
