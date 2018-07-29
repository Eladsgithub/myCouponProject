package com.example.demo.DAO;



import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Company;
import com.example.demo.exceptions.CompaniesNullListException;
import com.example.demo.exceptions.CompanyAllreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CouponsNullListException;
/***
 * Data Access Object for Company
 * @author Elad Cohen
 *
 */
@Service
public interface CompanyDAO {

		/***
		 * Creating new Company
		 * @param company
		 * @throws CompanyAllreadyExistsException
		 * @throws InterruptedException 
		 */
		void createCompany(Company company) throws CompanyAllreadyExistsException, InterruptedException;
		
		/***
		 * Removing Company by company object
		 * @param company
		 * @throws CompanyNotExistException
		 * @throws InterruptedException 
		 */
		void removeCompany(Company company) throws CompanyNotExistException, InterruptedException;
		
		/***
		 * Updating only email & password
		 * @param company
		 * @throws CompanyNotExistException
		 * @throws InterruptedException 
		 */
		void updateCompany(Company company)throws CompanyNotExistException, InterruptedException;
		
		/***
		 * Get Company by ID
		 * @param id
		 * @return Company
		 * @throws CompanyNotExistException
		 * @throws InterruptedException 
		 */
		Company getCompany(String compName)throws CompanyNotExistException, InterruptedException;
		
		/***
		 * Get all Companies
		 * @return ArrayList<Company>
		 * @throws CompaniesNullListException
		 * @throws InterruptedException 
		 */
		ArrayList<Company> getAllCompanies()throws CompaniesNullListException, InterruptedException;
		
		/***
		 * Get all Company Coupons
		 * @param companyId
		 * @return ArrayList<Company>
		 * @throws CompanyNotExistException
		 * @throws CouponsNullListException
		 * @throws InterruptedException 
		 */
//		ArrayList<Company> getCompanyCoupons(int companyId)throws CompanyNotExistException , CouponsNullListException, InterruptedException;
//		/***
//		 * Login for Company
//		 * @param compnayname
//		 * @param password
//		 * @return true if exist
//		 * @throws InterruptedException 
//		 */
		boolean login(String compnayname, String password) throws InterruptedException;

		
		
		
}
