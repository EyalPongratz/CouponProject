package com.example.couponsSpring.daos;

import com.example.couponsSpring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponDAO extends JpaRepository<Coupon, Integer> {
    public List<Coupon> findById(int companyID);

}

