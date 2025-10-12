package org.example.dziennikbackend.utils;

/**
 * Error to indicate database lack of searched resource
 * @version 1.0
 */
public class ResourceNotFoundException extends Exception {

    /**
     * Default constructor
     * @param message name of lacking resource
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * getMessage() Replacement
     * @return full message of error
     */
    public String toString(){
        return "Resource not found: " + getMessage();
    }
}
