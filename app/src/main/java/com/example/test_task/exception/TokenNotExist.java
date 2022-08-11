package com.example.test_task.exception;

public class TokenNotExist extends Exception {

    public TokenNotExist(String message) {
        super(message + " : Token doesn't exist");
    }
}
