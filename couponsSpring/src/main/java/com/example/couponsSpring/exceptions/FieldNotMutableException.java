package com.example.couponsSpring.exceptions;

public class FieldNotMutableException extends Exception{
    public FieldNotMutableException(String fieldName) {
        super("The field: '" + fieldName + "' is not mutable");
    }
}
