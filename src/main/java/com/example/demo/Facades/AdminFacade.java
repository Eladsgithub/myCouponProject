package com.example.demo.Facades;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.DBDAO.CompanyDBDAO;
import com.example.demo.DBDAO.CouponDBDAO;
import com.example.demo.DBDAO.CustomerDBDAO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Customer;
import com.example.demo.common.ClientType;
import com.example.demo.entry.CouponSystem;
import com.example.demo.exceptions.CompaniesNullListException;
import com.example.demo.exceptions.CompanyAllreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CustomerAllreadyExistsException;
import com.example.demo.exceptions.CustomerDoesNotExistException;

/**
 * 
 * @author Elad Cohen
 * Details all of the methods Admin can use
 *
 */

@Component
public class AdminFacade implements CouponClientFacade{
	
    @Autowired
    CompanyDBDAO companyDBDAO;
    @Autowired
    CustomerDBDAO customerDBDAO;
    @Autowired
    CouponDBDAO couponDBDAO;
    @Autowired
    CouponSystem couponSystem;
    /**
     * admin login if successful sends a facade
     * @param name
     * @param password
     * @param clinetType
     * @return this 
     */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clinetType){
		
		if (name.equals("admin") && password.equals("1234"))
//			logger.setAction("admin - succesfull login");
//			loggerDBDAO.logAction(logger);
				return this;
		else 
			{
			System.out.println("login failed please try again");
//			logger.setAction("admin - failed login");
//			loggerDBDAO.logAction(logger);
			}
		return null;
 
		}
	/**
	 * create company calls create company at companyDBDAO
	 * @param company
	 * @throws CompanyAllreadyExistsException
	 * @throws InterruptedException
	 */
	public void createCompany(Company company) throws CompanyAllreadyExistsException, InterruptedException{
		companyDBDAO.createCompany(company);

	}
	/**
	 * remove company calls remove company at companyDBDAO
	 * @param company
	 * @throws CompanyNotExistException
	 * @throws InterruptedException
	 */
	public void removeCompany(Company company) throws CompanyNotExistException, InterruptedException{
		companyDBDAO.removeCompany(company);
	}
	/**
	 * update company calls update company at companyDBDAO
	 * @param company
	 * @throws CompanyNotExistException
	 * @throws InterruptedException
	 */
	
	public void updateCompany(Company company) throws CompanyNotExistException, InterruptedException{
		companyDBDAO.updateCompany(company);
	}
	/**
	 * get company calls update get at companyDBDAO
	 * @param company
	 * @throws CompanyNotExistException
	 * @throws InterruptedException
	 */
	
	public Company getCompany(String compName) throws CompanyNotExistException, InterruptedException{
		return companyDBDAO.getCompany(compName);
	}
	/**
	 * gets all companies calls gets all companies at companyDBDAO
	 * @throws CompaniesNullListException
	 * @throws InterruptedException
	 */
	
	public Collection<Company> getAllCompanies() throws CompaniesNullListException, InterruptedException{
		return companyDBDAO.getAllCompanies();
	} 
	/**
	 * create Customer calls createCustomer at customerDBDAO
	 * @throws CompaniesNullListException
	 * @throws InterruptedException
	 */
	public void createCustomer(Customer customer) throws CustomerAllreadyExistsException, InterruptedException{
		customerDBDAO.createCustomer(customer);
	}
	/**
	 * remove Customer calls removeCustomer at customerDBDAO
	 * @throws CustomerDoesNotExistException
	 * @throws InterruptedException
	 */
	public void removeCustomer(String c) throws CustomerDoesNotExistException, InterruptedException{
		customerDBDAO.removeCustomer(c);
	}
	/**
	 * update Customer calls updateCustomer at customerDBDAO
	 * @throws CustomerDoesNotExistException
	 * @throws InterruptedException
	 */
	public void updateCustomer(Customer customer) throws InterruptedException, CustomerDoesNotExistException{
		customerDBDAO.updateCustomer(customer);
	}/**
	 * getAllCustomers calls getAllCustomers at customerDBDAO
	 * @throws InterruptedException
	 */
	public Collection<Customer> getAllCustomers() throws InterruptedException{
		return customerDBDAO.getAllCustomers();
	}
	/**
	 * getCustomer calls getCustomer at customerDBDAO
	 * @throws InterruptedException
	 */
	public Customer getCustomer(long id) throws InterruptedException{
		return customerDBDAO.getCustomer(id);
	}

	

	
}

