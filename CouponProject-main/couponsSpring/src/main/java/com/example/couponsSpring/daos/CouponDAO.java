package com.example.couponsSpring.daos;

import com.example.couponsSpring.beans.Category;
import com.example.couponsSpring.beans.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponDAO extends JpaRepository<Coupon, Integer> {
    public List<Coupon> findAllByCompanyId(int companyID);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_coupons " +
            "WHERE coupon_id IN " +
            "(SELECT cc.coupon_id " +
            "FROM customers_coupons cc " +
            "JOIN coupons c ON cc.coupon_id = c.coupon_id " +
            "WHERE c.company_id = :companyId)", nativeQuery = true)
    public void deleteCompanyCouponPurchases(int companyId);

//    @Query(value = "SELECT * FROM Coupons WHERE company_id = :companyId", nativeQuery = true)
//    List<Coupon> findCouponsByCompanyId(int companyId);

    @Query(value = "SELECT * FROM Coupons c WHERE c.company_id = :companyId and c.category = :category", nativeQuery = true)
    List<Coupon> findCouponsByCategory(int companyId, String category);

    List<Coupon> findAllByCompanyIdAndPriceLessThanEqual(int companyId, double price);
}

