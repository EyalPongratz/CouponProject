package com.example.couponsSpring.facades;

import com.example.couponsSpring.daos.CompanyDAO;
import com.example.couponsSpring.daos.CouponDAO;
import com.example.couponsSpring.daos.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public abstract class ClientFacade {
    @Autowired
    protected CompanyDAO companyDAO;
    @Autowired
    protected CouponDAO couponDAO;
    @Autowired
    protected CustomerDAO customerDAO;

    public abstract boolean login(String email, String password) throws SQLException;
}
