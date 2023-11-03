package com.example.couponsSpring.exceptions;

public class NoSuchCompanyException extends Exception{
    public NoSuchCompanyException(int companyID) {
        super("No company exists under ID: " + companyID);
    }
}
