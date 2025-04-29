package org.example.dziennikbackend.configs;

public class ConfigObject {
    private String tokenPassword;
    public ConfigObject(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
    public String getTokenPassword() {
        return tokenPassword;
    }
    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
}
