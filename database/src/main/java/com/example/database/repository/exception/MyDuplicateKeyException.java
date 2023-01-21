package com.example.database.repository.exception;

public class MyDuplicateKeyException extends MyDBException{
    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
