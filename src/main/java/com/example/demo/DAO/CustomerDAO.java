package com.example.demo.DAO;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.Customer;
import com.example.demo.exceptions.CustomerAllreadyExistsException;
import com.example.demo.exceptions.CustomerDoesNotExistException;

@Service
public interface CustomerDAO {

	/***
	 * Create new Customer
	 * 
	 * @param customer
	 * @throws CustomerAllreadyExistsException
	 * @throws InterruptedException 
	 */
	void createCustomer(Customer customer) throws CustomerAllreadyExistsException, InterruptedException;

	/***
	 * Remove new Customer
	 * 
	 * @param customer
	 * @throws InterruptedException 
	 * @throws CustomerDoesNotExistsException
	 */
	void removeCustomer(Customer customer) throws CustomerDoesNotExistException, InterruptedException;

	/**
	 * Update Customer set only password by ID
	 * 
	 * @param customer
	 * @throws InterruptedException 
	 * @throws CustomerDoesNotExistException 
	 */
	void updateCustomer(Customer customer) throws InterruptedException, CustomerDoesNotExistException;

	/***
	 * Get new Customer
	 * 
	 * @param id
	 * @return Customer
	 * @throws InterruptedException 
	 * @throws CustomerDoesNotExistException 
	 */
	Customer getCustomer(long id) throws InterruptedException, CustomerDoesNotExistException;

	/***
	 * Get all Customers
	 * 
	 * @return ArrayList<Customer>
	 * @throws InterruptedException 
	 * @throws CustomernulllistException
	 */
	ArrayList<Customer> getAllCustomers() throws InterruptedException;

	/***
	 * Get all Coupons
	 * 
	 * @param customerId
	 * @return ArrayList<Coupon>
	 * @throws CouponnulllistException
	 */
	ArrayList<Coupon> getCoupons(int customerId);

	/***
	 * system login as customer * @param custName * @param password
	 * 
	 * @return true if exist
	 * @throws InterruptedException 
	 * @throws CustomerDoesNotExistException
	 */
	boolean login(String custName, String password) throws InterruptedException;




}
