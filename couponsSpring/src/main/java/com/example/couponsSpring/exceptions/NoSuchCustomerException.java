package com.example.couponsSpring.exceptions;

public class NoSuchCustomerException extends Exception{
    public NoSuchCustomerException(int customerID) {
        super("No customer exists under ID: " + customerID);
    }
}
