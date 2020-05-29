package com.example.photoassistant.auth;

public class InvalidLoginException extends Exception {

    InvalidLoginException(String msg) {
        super(msg);
    }

    public InvalidLoginException() {
        super();
    }
}
