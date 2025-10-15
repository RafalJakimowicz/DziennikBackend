package org.example.dziennikbackend.utils;

/**
 * Indicated that there is conflict with request data and database data
 * @version 1.0
 */
public class ResourceConflictException extends RuntimeException {
    public String message;
    public ResourceConflictException(String message) {
        super(message);
        this.message = message;
    }

    public String toString()
    {
        return "Resource conflict for " + this.message;
    }
}
