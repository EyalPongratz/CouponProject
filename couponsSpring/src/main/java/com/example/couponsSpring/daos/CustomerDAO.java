package com.example.couponsSpring.daos;

import com.example.couponsSpring.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    public boolean existsByEmail(String email);
}
