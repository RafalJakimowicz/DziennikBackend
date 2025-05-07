package org.example.dziennikbackend.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
}
