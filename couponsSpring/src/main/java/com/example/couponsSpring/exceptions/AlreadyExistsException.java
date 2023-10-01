package com.example.couponsSpring.exceptions;

public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String value, String fieldName) {
        super("The value: " + value + ", for field name: " + fieldName + " already exists");
    }
}
