package com.example.couponsSpring.facades;

import com.example.couponsSpring.beans.Company;
import com.example.couponsSpring.beans.Customer;
import com.example.couponsSpring.exceptions.AlreadyExistsException;
import com.example.couponsSpring.exceptions.FieldNotMutableException;
import com.example.couponsSpring.exceptions.NoSuchCompanyException;
import com.example.couponsSpring.exceptions.NoSuchCustomerException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AdminFacade extends ClientFacade{

    /**
     * Authenticates a user with the provided email and password.
     *
     * This method checks if the provided email and password match the credentials of an admin user
     * in the system. If the email and password match the admin's credentials, the method returns true,
     * indicating a successful login. Otherwise, it returns false, indicating a failed login attempt.
     *
     * @param email The email address of the user attempting to log in.
     * @param password The password provided by the user for authentication.
     * @return true if the provided email and password match the admin's credentials, false otherwise.
     * @throws SQLException if there is an issue with the database during the login attempt.
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * Adds a new company to the system.
     *
     * This method checks if a company with the same name or email already exists in the system's data store
     * before saving the new company. If a company with the same name or email is found, it throws an
     * AlreadyExistsException with a specific error message indicating whether the duplication is due to
     * the company's name or email.
     *
     * @param company The Company object to be added to the system.
     * @throws AlreadyExistsException if a company with the same name or email already exists in the system.
     */
    public void addCompany(Company company) throws AlreadyExistsException {
        if (companyDAO.existsByName(company.getName()))
            throw new AlreadyExistsException(company.getName(), "Name");
        else if (companyDAO.existsByEmail(company.getEmail()))
            throw new AlreadyExistsException(company.getEmail(), "Email");
        else
            companyDAO.save(company);
    }

    /**
     * Updates the information of an existing company in the system.
     *
     * This method first checks if a company with the specified ID exists in the system's data store. If
     * the company does not exist, it throws a NoSuchCompanyException. Next, it compares the name of
     * the existing company with the updated name. If the name has changed, it throws a FieldNotMutableException
     * since the name is considered immutable. Finally, it saves the updated company information.
     *
     * @param company The Company object containing the updated information for an existing company.
     * @throws NoSuchCompanyException if no company with the specified ID exists in the system.
     * @throws FieldNotMutableException if the company's name is attempted to be changed, which is not allowed.
     * @throws Exception if an unexpected error occurs during the update.
     */
    public void updateCompany(Company company) throws Exception {
        if (!companyDAO.existsById(company.getId()))
            throw new NoSuchCompanyException(company.getId());

        Company temp = companyDAO.findById(company.getId()).orElseThrow();
        if(!temp.getName().equals(company.getName()))
            throw new FieldNotMutableException("name");

        companyDAO.save(company);
    }

    /**
     * Deletes a company with the specified company ID.
     *
     * @param companyID The ID of the company to be deleted.
     * @throws NoSuchCompanyException If no company with the given company ID exists.
     */
    public void deleteCompany(int companyID) throws NoSuchCompanyException {
        if (!companyDAO.existsById(companyID))
            throw new NoSuchCompanyException(companyID);
        else
            companyDAO.deleteById(companyID);
    }

    /**
     * Retrieves a list of all existing companies.
     *
     * @return A List of Company objects representing all existing companies.
     */
    public List<Company> getAllCompanies() {
        return companyDAO.findAll();
    }

    /**
     * Retrieves a company by its unique ID along with its associated coupons.
     *
     * This method searches for a company with the specified ID in the system's data store. If a company
     * with the given ID is found, it retrieves and sets its associated coupons using the CouponDAO.
     * The resulting Company object, including its associated coupons, is then returned.
     *
     * @param companyID The unique identifier of the company to retrieve.
     * @return The Company object with the specified ID and associated coupons.
     * @throws NoSuchCompanyException if no company with the specified ID exists in the system.
     */
    public Company getCompanyByID(int companyID) throws NoSuchCompanyException {
        return companyDAO.findById(companyID).orElseThrow(()->new NoSuchCompanyException(companyID));
    }

    /**
     * Adds a new customer to the system.
     *
     * This method checks if a customer with the same email already exists in the system's data store
     * before saving the new customer. If a customer with the same email is found, it throws an
     * AlreadyExistsException with a specific error message indicating that the duplication is due to
     * the customer's email.
     *
     * @param customer The Customer object to be added to the system.
     * @throws AlreadyExistsException if a customer with the same email already exists in the system.
     */
    public void addCustomer(Customer customer) throws AlreadyExistsException {
        if (customerDAO.existsByEmail(customer.getEmail()))
            throw new AlreadyExistsException(customer.getEmail(), "Email");
        else
            customerDAO.save(customer);
    }

    /**
     * Updates the information of an existing customer in the system.
     *
     * This method first checks if a customer with the specified ID exists in the system's data store.
     * If the customer does not exist, it throws a NoSuchCustomerException. Otherwise, it updates the
     * customer's information by saving the provided Customer object.
     *
     * @param customer The Customer object containing the updated information for an existing customer.
     * @throws NoSuchCustomerException if no customer with the specified ID exists in the system.
     */
    public void updateCustomer(Customer customer) throws NoSuchCustomerException {
        if (!customerDAO.existsById(customer.getId()))
            throw new NoSuchCustomerException(customer.getId());
        else
            customerDAO.save(customer);
    }

    /**
     * Deletes a customer with the specified customer ID.
     *
     * @param customerID The ID of the customer to be deleted.
     * @throws NoSuchCustomerException If no customer with the given customer ID exists.
     */
    public void deleteCustomer(int customerID) throws NoSuchCustomerException {
        if (!customerDAO.existsById(customerID))
            throw new NoSuchCustomerException(customerID);
        else
            customerDAO.deleteById(customerID);
    }

    /**
     * Retrieves a list of all existing customers.
     *
     * @return A List of Customer objects representing all existing customers.
     */
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    /**
     * Retrieves a customer with the specified customer ID.
     *
     * @param customerID The ID of the customer to retrieve.
     * @return A Customer object representing the customer with the given ID.
     * @throws NoSuchCustomerException If no customer with the given ID exists.
     */
    public Customer getCustomerByID(int customerID) throws NoSuchCustomerException {
        return customerDAO.findById(customerID).orElseThrow(()->new NoSuchCustomerException(customerID));
    }
}
