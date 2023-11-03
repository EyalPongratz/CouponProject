package com.example.couponsSpring.facades;

import com.example.couponsSpring.beans.Category;
import com.example.couponsSpring.beans.Company;
import com.example.couponsSpring.beans.Coupon;
import com.example.couponsSpring.exceptions.AlreadyExistsException;
import com.example.couponsSpring.exceptions.FieldNotMutableException;
import com.example.couponsSpring.exceptions.NoSuchCompanyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompanyFacade extends ClientFacade {
    private int companyId;

    @Override
    public boolean login(String email, String password) throws SQLException {
        if (companyDAO.existsByEmailAndPassword(email, password)) {
            Company company = companyDAO.findByEmail(email);
            if (company.getPassword().equals(password)) {
                companyId = company.getId();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new coupon to the company's existing list of coupons.
     * <p>
     * This method includes the provided coupon in the list of coupons for the company if a coupon with a similar title does not already exist.
     * It retrieves the company by its ID and checks if a coupon with the same title already exists for the company. If an existing coupon with the same title is found,
     * it throws an AlreadyExistsException. Otherwise, it adds the new coupon to the company's list of coupons and saves the updated company information.
     *
     * @param coupon The coupon to be added to the company's list of coupons.
     * @throws NoSuchCompanyException if no company is found with the given companyID.
     * @throws AlreadyExistsException if a coupon with the same title already exists for the company.
     */
    public void addCoupon(Coupon coupon) throws NoSuchCompanyException, AlreadyExistsException {
        Company company = companyDAO.findById(companyId).orElseThrow(()-> new NoSuchCompanyException(companyId));
        for (Coupon c: company.getCoupons()) {
            if (c.getTitle().equals(coupon.getTitle()))
                throw new AlreadyExistsException("A coupon with this title already exists for this company");
        }
        company.getCoupons().add(coupon);
        companyDAO.save(company);
    }

    /**
     * Updates an existing coupon in the system.
     * <p>
     * This method modifies the details of the provided coupon if it already exists in the system. It checks for the existence of the coupon based on its ID,
     * verifies if the provided coupon belongs to the same company as the existing coupon, and then updates the coupon information.
     *
     * @param coupon The coupon containing the updated information to be modified in the system.
     * @throws FieldNotMutableException if attempting to modify a non-mutable field, such as changing the company association.
     * @throws NoSuchElementException if no coupon is found with the given coupon ID.
     */
    public void updateCoupon(Coupon coupon) throws FieldNotMutableException, NoSuchElementException {
        Coupon c = couponDAO.findById(coupon.getId()).orElseThrow(()->
                new NoSuchElementException("No coupon exists with id: " + coupon.getId()));
        if (c.getCompany().getId() != coupon.getCompany().getId())
            throw new FieldNotMutableException("company");
        else
            couponDAO.save(coupon);
    }

    /**
     * Retrieves a coupon by its unique identifier.
     * <p>
     * This method fetches and returns the coupon associated with the provided ID. If a coupon with the specified ID
     * does not exist in the system, it throws a NoSuchElementException.
     *
     * @param id The unique identifier of the coupon to retrieve.
     * @return The coupon associated with the provided ID.
     * @throws NoSuchElementException if no coupon is found with the given ID.
     */
    public Coupon getCouponById(int id) throws NoSuchElementException {
        return couponDAO.findById(id).orElseThrow(()-> new NoSuchElementException("No coupon exists with id: " + id));
    }

    public List<Coupon> getCompanyCoupons() {
        return couponDAO.findAllByCompanyId(companyId);
    }

    public List<Coupon> getCouponsByCategory(Category category) {
        return couponDAO.findCouponsByCategory(companyId, category.toString());
    }

    public List<Coupon> getCouponsUpToPrice(double price) {
        return couponDAO.findAllByCompanyIdAndPriceLessThanEqual(companyId, price);
    }

    public Company getCompanyDetails() {
        return companyDAO.findById(companyId).orElseThrow(NoSuchElementException::new);
    }
}
