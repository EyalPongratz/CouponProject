package com.example.couponsSpring;

import com.example.couponsSpring.beans.Category;
import com.example.couponsSpring.beans.Company;
import com.example.couponsSpring.beans.Coupon;
import com.example.couponsSpring.beans.Customer;
import com.example.couponsSpring.daos.CouponDAO;
import com.example.couponsSpring.exceptions.AlreadyExistsException;
import com.example.couponsSpring.facades.AdminFacade;
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

//		Company company = new Company("Epic", "epic@gmail.com", "bla", new ArrayList<>());

//		Customer customer = new Customer("John", "Smith", "js@gmail.com", "blabla",new ArrayList<>());

//		Coupon coupon = new Coupon(adminFacade.getCompanyByID(1), Category.ELECTRICITY, "games", "video games",
//				generateDate("1/1/2023"), generateDate("12/12/2023"), 10, 100, "image");
//
//		CouponDAO couponDAO = context.getBean(CouponDAO.class);
//		couponDAO.save(coupon);
		System.out.println(adminFacade.getCompanyByID(1));
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
