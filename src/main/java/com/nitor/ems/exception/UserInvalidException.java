package com.nitor.ems.exception;

public class UserInvalidException extends RuntimeException {
    public UserInvalidException(String message) {
        super(message);
    }
}
