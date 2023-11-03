package com.example.couponsSpring.facades;

import com.example.couponsSpring.beans.Coupon;
import com.example.couponsSpring.beans.Customer;
import com.example.couponsSpring.exceptions.NoSuchCustomerException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerFacade extends ClientFacade{
    private int customerID;

    /**
     * Validates the login credentials of a customer.
     *
     * This method checks whether the provided email and password combination matches any existing customer in the system.
     * If a customer with the given email and password is found, it sets the customerID for the session and returns true, indicating successful login.
     * If the provided credentials are invalid or do not match any existing customer, it returns false.
     *
     * @param email The email of the customer attempting to log in.
     * @param password The password of the customer attempting to log in.
     * @return true if the login is successful, false otherwise.
     * @throws SQLException if an SQL-related error occurs during the login process.
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        if (customerDAO.existsByEmailAndPassword(email, password)) {
            Customer customer = customerDAO.findByEmail(email);
            if(customer.getPassword().equals(password)) {
                customerID = customer.getId();
                return true;
            }
        }
        return false;
    }

    public void purchaseCoupon(Coupon coupon) throws NoSuchCustomerException {
        Customer customer = customerDAO.findById(customerID).orElseThrow(()-> new NoSuchCustomerException(customerID));
        customer.getCoupons().add(coupon);
        customerDAO.save(customer);
    }
}
