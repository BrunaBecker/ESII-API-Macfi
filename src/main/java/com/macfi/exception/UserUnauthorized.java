package com.macfi.exception;

public class UserUnauthorized extends RuntimeException {
    public UserUnauthorized(String message) {
        super(message);
    }
}
