package com.example.couponsSpring;

import com.example.couponsSpring.beans.Category;
import com.example.couponsSpring.beans.Company;
import com.example.couponsSpring.beans.Coupon;
import com.example.couponsSpring.beans.Customer;
import com.example.couponsSpring.daos.CouponDAO;
import com.example.couponsSpring.exceptions.AlreadyExistsException;
import com.example.couponsSpring.facades.AdminFacade;
import com.example.couponsSpring.facades.CompanyFacade;
import com.example.couponsSpring.facades.CustomerFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CouponsSpringApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(CouponsSpringApplication.class, args);

		AdminFacade adminFacade = context.getBean(AdminFacade.class);
		CustomerFacade customerFacade = context.getBean(CustomerFacade.class);
		CompanyFacade companyFacade = context.getBean(CompanyFacade.class);

//		Company company = new Company("Steam", "steam@gmail.com", "bla", new ArrayList<>());

//		Customer customer = new Customer("John", "Smith", "js@gmail.com", "blabla",new ArrayList<>());

//		Coupon coupon = new Coupon(adminFacade.getCompanyById(1), Category.ELECTRICITY, "games", "video games",
//				generateDate("1/1/2023"), generateDate("12/12/2023"), 11, 100, "image");
//
//		CouponDAO couponDAO = context.getBean(CouponDAO.class);
//		couponDAO.save(coupon);
		//System.out.println(adminFacade.getCompanyByID(1));
//		Coupon coupon = companyFacade.getCouponById(1);
//		coupon.setCompany(adminFacade.getCompanyById(2));

		System.out.println(customerFacade.login("js@gmail.com", "blabla"));
		System.out.println(companyFacade.login("epic@gmail.com", "bla"));
//		companyFacade.updateCoupon(coupon);
//		adminFacade.addCompany(company);
		System.out.println(companyFacade.getCompanyDetails());
	}

	public static java.sql.Date generateDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;

		try {
			utilDate = dateFormat.parse(dateString);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return sqlDate;
	}
}
