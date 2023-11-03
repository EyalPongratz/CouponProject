package com.example.couponsSpring.exceptions;

public class NoSuchElementException extends Exception {
    public NoSuchElementException() {
        super("No such element");
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
