package org.example.dziennikbackend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    public String message;
    public Date timestamp;
    public String error;
    public Integer status;

    public ErrorMessage getObject(String error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.timestamp = Date.from(Instant.now());
        this.status = status;
        return this;
    }
}
