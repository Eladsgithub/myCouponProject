package com.example.demo.DBDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.CustomerDAO;
import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.Customer;
import com.example.demo.Facades.CompanyFacade;
import com.example.demo.common.CouponType;
import com.example.demo.connection.ConnectionPool;
import com.example.demo.connection.DBConnection;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponPurchaseunsuccesfulAmountException;
import com.example.demo.exceptions.CouponPurchaseunsuccesfulEndDateExceededException;
import com.example.demo.exceptions.CustomerAllreadyExistsException;
import com.example.demo.exceptions.CustomerDoesNotExistException;
/**
 * 
 * @author Elad Cohen
 *
 */
@Service
public class CustomerDBDAO implements CustomerDAO {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;
	
	//creating a 
	Customer custLoggedIn;
	/**
	 * customer login
	 * @param custName
	 * @param password
	 * @throws InterruptedException
	 * @return boolean
	 */
	@Override
	public boolean login(String custName, String password) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Customer cust =null;
		if((cust = customerRepo.findByCustNameAndPassword(custName, password))!=null){
			ConnectionPool.getInstance().returnConenction(dbConnection);
			this.custLoggedIn = cust;
//		logger.setAction("Customer - succesful login");
//		loggerDBDAO.logAction(logger);
			return true;
	}
	else 
		{
		System.out.println("login failed please try again");
//		logger.setAction("Customer - failed login");
//		loggerDBDAO.logAction(logger);
		}
			return false;
}
	/**
	 * creating customer  
	 * @param customer
	 * @throws InterruptedException
	 * @throws CustomerAllreadyExistsException
	 *  
	 */
	@Override
	public void createCustomer(Customer customer) throws CustomerAllreadyExistsException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Customer custFromDB = customerRepo.findByCustName(customer.getCustName());
			if (custFromDB == null){
			customerRepo.save(customer);
			ConnectionPool.getInstance().returnConenction(dbConnection);
			}
		else throw new CustomerAllreadyExistsException("customer Allready Exists");{
			ConnectionPool.getInstance().returnConenction(dbConnection);}
			
		
	}
	/**
	 * removing customer  
	 * @param customer
	 * @throws InterruptedException
	 * @throws CustomerDoesNotExistException
	 *  
	 */
	@Override
	public void removeCustomer(String c) throws CustomerDoesNotExistException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Customer custFromDB = customerRepo.findByCustName(c);
		if (custFromDB != null){
		customerRepo.delete(custFromDB);
		ConnectionPool.getInstance().returnConenction(dbConnection);}
		else throw new CustomerDoesNotExistException("no such customer exists");{
			ConnectionPool.getInstance().returnConenction(dbConnection);
		}
	}
	/**
	 * updating customer  
	 * @param customer
	 * @throws InterruptedException
	 * @throws CustomerDoesNotExistException
	 *  
	 */
	@Override
	public void updateCustomer(Customer customer) throws InterruptedException, CustomerDoesNotExistException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Customer customerFromDB = customerRepo.findByCustName(customer.getCustName());
		if (customerFromDB != null){
		customerFromDB.setPassword(customer.getPassword());
		customerFromDB.setCoupons(customer.getCoupons());
		customerRepo.save(customerFromDB);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		}
		else throw new CustomerDoesNotExistException("no such customer exists");{
			ConnectionPool.getInstance().returnConenction(dbConnection);
		}
		
		
	}
	/**
	 * get customer  
	 * @param id
	 * @throws InterruptedException
	 * @throws CustomerDoesNotExistException
	 * @return customer
	 */
	@Override
	public Customer getCustomer(long id) throws InterruptedException{
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Customer CustfromDB = customerRepo.findById(id);
		if(CustfromDB != null)
		ConnectionPool.getInstance().returnConenction(dbConnection);
		return CustfromDB;
	}
	/**
	 * get all customers  
	 * @throws InterruptedException
	 * @return customer list 
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Customer> customerLIst = new ArrayList<>();
		customerLIst = (ArrayList<Customer>) customerRepo.findAll();
		ConnectionPool.getInstance().returnConenction(dbConnection);
		return customerLIst;
	}
	/**
	 * get all customers  
	 * @throws InterruptedException
	 * @return customer list 
	 */
	@Override
	public ArrayList<Coupon> getCoupons(int customerId) {
		return null;
	}
	/**
	 * purchase coupon 
	 * @param coupon
	 * @param custLoggedInId
	 * @throws InterruptedException
	 * @throws CouponDoesNotExistsException
	 * @throws CouponPurchaseunsuccesfulEndDateExceededException
	 * @throws CouponPurchaseunsuccesfulAmountException
	 * @throws InterruptedException
	 * @return customer list 
	 * @throws CustomerDoesNotExistException 
	 */
	public void purchaseCoupon(Coupon coupon) throws CouponDoesNotExistsException, CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, InterruptedException, CustomerDoesNotExistException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Coupon couponTest =  couponRepo.findByTitle(coupon.getTitle());
		ConnectionPool.getInstance().returnConenction(dbConnection);
			if (couponTest == null)
			{
			throw new CouponDoesNotExistsException("Coupon ID # +" + coupon.getId() + "does not exist");
		}
		// Checking if Customer hold this Coupon
		DBConnection dbConnection1 = ConnectionPool.getInstance().getConnection();
		Coupon customerCoupon = couponRepo.findByIdAndCustomersId(coupon.getId(), custLoggedIn.getId());
		ConnectionPool.getInstance().returnConenction(dbConnection1);
		if (customerCoupon != null)
		{
			throw new CouponPurchaseunsuccesfulAmountException("customer can obtain only one coupon of a kind");
		}
		// Checking amount
		if (couponTest.getAmount() < 1) 
		{
			throw new CouponPurchaseunsuccesfulAmountException("no coupons left in stock");
		}
			// Checking expired date
			if (couponTest.getEndDate().before(new Date(System.currentTimeMillis()))) {
				
				throw new CouponPurchaseunsuccesfulEndDateExceededException("Coupon expired");
			}
				else 
				{
					DBConnection dbConnection2 = ConnectionPool.getInstance().getConnection();
	//					customerRepo.purchaseCustomerCoupon(coupon.getId());
					System.out.println("Purchasing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					customerRepo.updateAmount(couponTest.getId());
					custLoggedIn.addCoupon(couponTest);
					this.updateCustomer(custLoggedIn);
					ConnectionPool.getInstance().returnConenction(dbConnection2);
				}
	}
		/**
		 * generates a list of all purchased coupons
		 * @param customerId
		 * @return custCouponList
		 * @throws InterruptedException
		 */
		public ArrayList<Coupon> getAllPurchasedCoupons(long customerId) throws InterruptedException {
			DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
			ArrayList<Coupon> custCouponList = (ArrayList<Coupon>) customerRepo.getAllPurchasedCouponByCustomer(this.custLoggedIn.getId());
			ConnectionPool.getInstance().returnConenction(dbConnection);
			return custCouponList;
	}
		/**
		 * generates a list of all purchased coupons by type 
		 * @param customerId
		 * @return custCouponListBtType
		 * @throws InterruptedException
		 */
		public ArrayList<Coupon> getAllPurchasedCouponsByType(CouponType type, long customerId) throws InterruptedException {
			DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
			ArrayList<Coupon> custCouponListBtType = (ArrayList<Coupon>) customerRepo.getAllPurchasedCouponByType(this.custLoggedIn.getId(), type);
			ConnectionPool.getInstance().returnConenction(dbConnection);
			return custCouponListBtType;
		}
		/**
		 * generates a list of all purchased coupons by price 
		 * @param customerId
		 * @return custCouponListBtPrice
		 * @throws InterruptedException
		 */
		public ArrayList<Coupon> getAllPurchasedCouponsByPrice(double price, long customerId) throws InterruptedException {
			DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
			ArrayList<Coupon> custCouponListBtPrice = (ArrayList<Coupon>) customerRepo.getAllPurchasedCouponByPrice(this.custLoggedIn.getId(), price);
			ConnectionPool.getInstance().returnConenction(dbConnection);			
			return custCouponListBtPrice;
		}

		
}



