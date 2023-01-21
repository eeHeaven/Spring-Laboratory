package com.example.database.repository.exception;

public class MyDBException extends RuntimeException{
    public MyDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
