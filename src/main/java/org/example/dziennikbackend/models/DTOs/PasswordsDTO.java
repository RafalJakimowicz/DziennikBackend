package org.example.dziennikbackend.models.DTOs;

public class PasswordsDTO {
    private String oldPassword;
    private String newPassword;
    public PasswordsDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public PasswordsDTO() {}

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }
}
