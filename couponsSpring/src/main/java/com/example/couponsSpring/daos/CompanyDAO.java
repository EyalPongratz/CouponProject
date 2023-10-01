package com.example.couponsSpring.daos;

import com.example.couponsSpring.beans.Company;
import com.example.couponsSpring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Integer> {
    public boolean existsByEmail(String email);

    public boolean existsByName(String name);
}
