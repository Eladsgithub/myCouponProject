package com.example.demo.DBDAO;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.CompanyDAO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.MyLogger;
import com.example.demo.Facades.CompanyFacade;
import com.example.demo.common.CouponType;
import com.example.demo.connection.ConnectionPool;
import com.example.demo.connection.DBConnection;
import com.example.demo.exceptions.CompaniesNullListException;
import com.example.demo.exceptions.CompanyAllreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistException;

/***
 *
 * @author Elad Cohen
 *
 */
@Service
public class CompanyDBDAO implements CompanyDAO{

	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	CompanyFacade companyFacade;
	@Autowired
	MyLoggerDBDAO myLoggerDBDAO;
	@Autowired
	MyLoggerRepo myLoggerRepo;
	
	
	private Company compLoggedIn = null;
	//instance of Logger to with date inside to log action message 
	MyLogger myLogger = new MyLogger(new Date(), "action");
	
	
	public Company getCompLoggedIn() {
		return compLoggedIn;
	}
	/**
	 * login method for company
	 * 
	 */
	@Override
	public boolean login(String compName, String password) throws InterruptedException 
	{
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		if(companyRepo.findByCompNameAndPassword(compName, password) != null){
			ConnectionPool.getInstance().returnConenction(dbConnection);
			myLogger.setAction("Company - succesfull login");
			myLoggerDBDAO.logAction(myLogger);
				return true;
		}
		else
		{
			System.out.println("login failed please try again");
			myLogger.setAction("Company - failed login");
			myLoggerDBDAO.logAction(myLogger);
		}
				return false;
	}
			 
	/**
	 * Creating new Company
	 * @param company
	 * @throws InterruptedException 
	 * @throws CompanyAllreadyExistsException
	 *   
	 */
	@Override
	public void createCompany(Company company) throws CompanyAllreadyExistsException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Company compFomDB = companyRepo.findByCompName(company.getCompName());
		if (compFomDB == null){
		companyRepo.save(company);
		myLogger.setAction("company created" + company.getCompName() + company.getId());
		myLoggerDBDAO.logAction(myLogger);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		}
		else
			{
		myLogger.setAction("company creation failed" + company.getCompName());
		myLoggerRepo.save(myLogger);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		throw new CompanyAllreadyExistsException("company Allready Exists");
		
	}
	}
	
	/**
	 * Removing new Company
	 * @param company
	 * @throws InterruptedException 
	 * @throws CompanyNotExistException
	 */
	@Override
	public void removeCompany(Company company) throws CompanyNotExistException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Company compFomDB = companyRepo.findByCompName(company.getCompName());
		if(compFomDB != null){
			companyRepo.delete(compFomDB);
			myLogger.setAction("company removed" + company.getCompName() + company.getId());
			myLoggerDBDAO.logAction(myLogger);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		}
		else 
			{
			myLogger.setAction("company removed failed" + company.getCompName() + company.getId() + "company does not exist");
			myLoggerDBDAO.logAction(myLogger);
			ConnectionPool.getInstance().returnConenction(dbConnection);
			throw new CompanyNotExistException("company does not exist");
		}
		
	}

	/**
	 * updating company
	 * @param company
	 * @throws CompanyNotExistException
	 * @throws InterruptedException
	 */
	@Override
	public void updateCompany(Company company) throws CompanyNotExistException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Company compFomDB = companyRepo.findByCompName(company.getCompName());
		if(compFomDB!=null)
		{
		compFomDB.setPassword(company.getPassword());
		compFomDB.setEmail(company.getEmail());
		compFomDB.setCoupons(company.getCoupons());
		companyRepo.save(compFomDB);
		myLogger.setAction("company updated" + company.getCompName() + compFomDB.getId());
		myLoggerDBDAO.logAction(myLogger);
		ConnectionPool.getInstance().returnConenction(dbConnection);
	
		}
		else 
			{
			myLogger.setAction("company update failed " + company.getCompName() + company.getId() + "company does not exist");
			myLoggerDBDAO.logAction(myLogger);
			ConnectionPool.getInstance().returnConenction(dbConnection);
			throw new CompanyNotExistException("company does not exist");
		}
		
	}
	/**
	 * get company
	 * @param id
	 * @throws CompanyNotExistException
	 * @throws InterruptedException
	 * @return company
	 */
	@Override
	public Company getCompany(String compName) throws CompanyNotExistException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Company compFromDB = companyRepo.findByCompName(compName);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		if(compFromDB ==null){
			myLogger.setAction("company update failed company does not exist");
			myLoggerDBDAO.logAction(myLogger);
			throw new CompanyNotExistException("company does not exist");
			}
		else {
			myLogger.setAction("get company company name" + compName);
			myLoggerDBDAO.logAction(myLogger);
			return compFromDB;

		}
	}
	/**
	 * get all companies in DB
	 * @throws CompaniesNullListException
	 * @throws InterruptedException
	 * @return company list
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws CompaniesNullListException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Company> listOfAllCompanies  = (ArrayList<Company>) companyRepo.findAll();
		ConnectionPool.getInstance().returnConenction(dbConnection);
		myLogger.setAction("getAllCompanies ");
		myLoggerDBDAO.logAction(myLogger);
		return listOfAllCompanies;
	}

//	@Override
//	public ArrayList<Company> getCompanyCoupons(int companyId)
//			throws CompanyNotExistException, CouponsNullListException, InterruptedException {
//		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
//		return null;
//	}

	
	public ArrayList<Coupon> getAllCoupons(long login_id) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) companyRepo.getAllCoupons(login_id);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		return coupons;
	}

	public ArrayList<Coupon> getAllCouponsByType(CouponType couponType, long login_id) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) companyRepo.getAllCouponsByType(login_id, couponType);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		return coupons;
	}


	public ArrayList<Coupon> getAllCouponsByPrice(double price, long login_id) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> coupons =  (ArrayList<Coupon>) companyRepo.getAllCouponsByPrice(login_id, price);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		return coupons;
	}
	
	public ArrayList<Coupon> getAllCouponsByDate(Date endDate, long login_id) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> coupons =  (ArrayList<Coupon>) companyRepo.getAllCouponsEndDate(login_id, endDate);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		return coupons;
	}
}


	


