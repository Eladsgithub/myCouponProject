//package com.example.demo;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.annotation.DirtiesContext.ClassMode;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.example.demo.DBDAO.CompanyDBDAO;
//import com.example.demo.DBDAO.CompanyRepo;
//import com.example.demo.DBDAO.CouponRepo;
//import com.example.demo.DBDAO.CustomerDBDAO;
//import com.example.demo.DBDAO.CustomerRepo;
//import com.example.demo.Entity.Company;
//import com.example.demo.Entity.Coupon;
//import com.example.demo.Entity.Customer;
//import com.example.demo.Facades.AdminFacade;
//import com.example.demo.Facades.CompanyFacade;
//import com.example.demo.Facades.CustomerFacade;
//import com.example.demo.common.ClientType;
//import com.example.demo.common.CouponType;
//import com.example.demo.common.DateMaker;
//import com.example.demo.connection.MyRunnable;
//import com.example.demo.entry.CouponSystem;
//import com.example.demo.exceptions.CompaniesNullListException;
//import com.example.demo.exceptions.CompanyAllreadyExistsException;
//import com.example.demo.exceptions.CompanyNotExistException;
//import com.example.demo.exceptions.CouponAllreadyExistsException;
//import com.example.demo.exceptions.CouponDoesNotExistsException;
//import com.example.demo.exceptions.CouponPurchaseunsuccesfulAmountException;
//import com.example.demo.exceptions.CouponPurchaseunsuccesfulEndDateExceededException;
//import com.example.demo.exceptions.CouponsNullListException;
//import com.example.demo.exceptions.CustomerAllreadyExistsException;
//import com.example.demo.exceptions.CustomerDoesNotExistException;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//public class CProjectApplicationTests {
//	
//	@Autowired
//	private CompanyDBDAO companyDBDAO;
//	@Autowired
//	private CustomerDBDAO customerDBDAO;
//	@Autowired
//	private CompanyRepo companyRepo;
//	@Autowired
//	private AdminFacade adminFacade;
//	@Autowired
//	private CustomerRepo customerRepo;
//	@Autowired
//	private CompanyFacade companyFacade;
//	@Autowired
//	private CouponRepo couponRepo;
//	@Autowired
//	private CustomerFacade customerFacade;
//	
//	@Autowired
//	CouponSystem cs;
//	@Autowired
//	ApplicationContext ctx;
//	
//
//	@AfterClass
//	public static void afterAll() {
//		System.out.println("#######################");
//	}
//	
//	//initiate daily thread
//
//	@Test 
//	public void dailyThreadTest()
//	{
//		Thread t = new Thread(new MyRunnable(ctx));
//	}
//		
//	@Test
//	public void contextLoads() 
//	{
//		
//	}
//
//	
//	/***
//	 * Test for Admin facade Create company
//	 */
//	@Test
//	public void test_01_AdminFacade_createCompany() throws InterruptedException {
//		Company company = new Company("Aroma", "1234", "aroma@gmail.com");
//		try {
//			
//				adminFacade.createCompany(company);
//				Company companyFromDB = companyRepo.findByCompName(company.getCompName()); 
//				Assert.assertEquals(company.getCompName(), companyFromDB.getCompName());
//				Assert.assertEquals(company.getEmail(), companyFromDB.getEmail());
//				Assert.assertEquals(company.getPassword(), companyFromDB.getPassword());
//			} 
//		catch (CompanyAllreadyExistsException e) {
//		}
//	}
//		/**
//		 * @throws CompanyNotExistException 
//		 * @throws CompaniesNullListException 
//		 * @throws InterruptedException 
//		 * 
//		 */
//	@Test
//		public void test_02_AdminFacade_removeCompany() throws CompanyNotExistException, CompaniesNullListException, InterruptedException {
//	
//			try {
//				Company company = new Company("Aroma", "1234", "aroma@gmail.com");
//				companyDBDAO.createCompany(company);
//				ArrayList<Company> companyList = (ArrayList<Company>) adminFacade.getAllCompanies();
//				Assert.assertEquals(1, companyList.size());
//				companyDBDAO.removeCompany(company);
//				ArrayList<Company> companyList1 = (ArrayList<Company>) adminFacade.getAllCompanies();
//				Assert.assertEquals(0, companyList1.size());
//				
//			} 
//			catch (CompanyAllreadyExistsException e) {
//			}
//	}
////	@Test
////	public void test_03_AdminFacade_updateCompany() throws CompanyNotExistException, InterruptedException
////	{
////		try {
////			Company company = new Company("Aroma", "1234", "aroma@gmail.com");
////			adminFacade.createCompany(company);
////			Company compFromDB = adminFacade.getCompany(company.getId());
////			Company company2 = adminFacade.getCompany(company.getId());
////			company2.setPassword("5678");
////			company2.setEmail("broma@bmail.com"); 
////			adminFacade.updateCompany(company2);
////			Assert.assertNotEquals(compFromDB.getPassword(), company2.getPassword());
////			Assert.assertNotEquals(compFromDB.getEmail(), company2.getEmail());
////		}
////	catch (CompanyAllreadyExistsException e) {
////	}
////		
////	}
////	@Test
////	public void test_04_AdminFacade_getCompany() throws CompanyNotExistException, InterruptedException
////	{
////		try {
////			Company company = new Company("Aroma", "1234", "aroma@gmail.com");
////			adminFacade.createCompany(company);
////			Company compFromDB = adminFacade.getCompany(company.getId());
////			Assert.assertEquals(company.getCompName(), compFromDB.getCompName());
////			Assert.assertEquals(company.getPassword(), compFromDB.getPassword());
////			Assert.assertEquals(company.getEmail(), compFromDB.getEmail());
////		}
////	catch (CompanyAllreadyExistsException e) {
////	}
////	
////		}
//	@Test
//	public void test_05_AdminFacade_getAllCompanies() throws CompanyNotExistException, CompaniesNullListException, InterruptedException
//	{
//		try {
//			Company company = new Company("Aroma", "1234", "aroma@gmail.com");
//			Company company2 = new Company("Brooks", "5678", "Brooks@gmail.com");
//			Company company3 = new Company("Crocs", "8901", "Crocs@gmail.com");
//			adminFacade.createCompany(company);
//			adminFacade.createCompany(company2);
//			adminFacade.createCompany(company3);
//			ArrayList<Company> listOfAllCompanies = (ArrayList<Company>) adminFacade.getAllCompanies();
//			Assert.assertEquals(3, listOfAllCompanies.size());
//		}
//	catch (CompanyAllreadyExistsException e) {
//	}
//	
//		}
//	@Test
//	public void test_06_AdminFacade_createCustomer() throws CustomerAllreadyExistsException, InterruptedException   
//	{
//		try {
//			Customer customer = new Customer("Adir", "Alibaba");
//			adminFacade.createCustomer(customer);
//			Customer customerFromDb = customerRepo.findOne(customer.getId());
//			Assert.assertEquals(customer.getCustName(), customerFromDb.getCustName());
//			Assert.assertEquals(customer.getPassword(), customerFromDb.getPassword());
//			
//			
//		}
//	catch (CustomerAllreadyExistsException e) {
//	}
//		}
//	
//	@Test
//	public void test_07_AdminFacade_removeCustomer() throws CustomerDoesNotExistException, InterruptedException   
//	{
//		try {
//			Customer customer = new Customer("Adir", "Alibaba");
//			adminFacade.createCustomer(customer);
//			ArrayList<Customer> customersLIst = new ArrayList<>();
//			customersLIst = (ArrayList<Customer>) adminFacade.getAllCustomers();
//			Assert.assertEquals(1, customersLIst.size());
//			adminFacade.createCustomer(customer);
//			Customer customerFromDb = customerRepo.findOne(customer.getId());
//			customersLIst = (ArrayList<Customer>) adminFacade.getAllCustomers();
//			Assert.assertEquals(1, customersLIst.size());
//			
//			
//		}
//	catch (CustomerAllreadyExistsException e) {
//	}
//		}
//	@Test
//	public void test_08_AdminFacade_updateCustomer() throws CustomerAllreadyExistsException, InterruptedException, CustomerDoesNotExistException   
//	{
//		
//		try {
//			Customer customer = new Customer("Adir", "Alibaba");
//			adminFacade.createCustomer(customer);
//			Customer customerFromDb = adminFacade.getCustomer(customer.getId());
//			Customer customer2 = adminFacade.getCustomer(customer.getId());
//			customer2.setPassword("Balibaba");
//			adminFacade.updateCustomer(customer2);
//			Customer customer3 = adminFacade.getCustomer(customer.getId());
//			Assert.assertNotEquals(customer3.getPassword(), customerFromDb.getPassword());
//		}
//	catch (CustomerAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//	}
//	@Test
//	public void test_09_AdminFacade_getCustomer() throws CustomerDoesNotExistException, InterruptedException  
//	{
//		
//		try {
//			Customer customer = new Customer("Adir", "Alibaba");
//			adminFacade.createCustomer(customer);
//			Customer customerFromDb = adminFacade.getCustomer(customer.getId());
//			Assert.assertEquals(customer.getPassword(), customerFromDb.getPassword());
//			Assert.assertEquals(customer.getCustName(), customerFromDb.getCustName());
//		}
//	catch (CustomerAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//	}
//	@Test
//	public void test_10_AdminFacade_getAllCustomers() throws InterruptedException   
//	{
//		
//		try {
//			Customer customer1 = new Customer("Adir", "Alibaba");
//			Customer customer2 = new Customer("Bob", "Bombay");
//			Customer customer3 = new Customer("Carl", "Alibaba");
//			adminFacade.createCustomer(customer1);
//			adminFacade.createCustomer(customer2);
//			adminFacade.createCustomer(customer3);
//			ArrayList<Customer> listOfAllCustomers = (ArrayList<Customer>) customerRepo.findAll();
//			Assert.assertEquals(3, listOfAllCustomers.size());
//			
//		}
//	catch (CustomerAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//	}
//	@Test
//	public void test_11_CompanyFacade_createCoupon() throws CouponAllreadyExistsException, CouponDoesNotExistsException, CompanyNotExistException, CompanyAllreadyExistsException, InterruptedException  
//	{
////		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
////		     adminFacade.createCompany(compLoggedIn2);
//		Coupon coupon = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
//		CompanyFacade compLoggedIn2  = (CompanyFacade) cs.login("TEVA", "1234", ClientType.COMPANY);
//		compLoggedIn2.createCoupon(coupon);
////		try {
////			Coupon coupon = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
////			coupon.setCompany(compLoggedIn2); 
////			companyFacade.createCoupon(coupon);
////			Coupon couponFromDB = companyFacade.getCoupon(coupon.getId());
////			Assert.assertEquals(coupon.getTitle(), couponFromDB.getTitle());
////			Assert.assertEquals(coupon.getMessage(), couponFromDB.getMessage());
////			Assert.assertEquals(coupon.getType(), couponFromDB.getType());
////		}
////	catch (CouponAllreadyExistsException e) {
////		e.printStackTrace();
////	}
//	}
//	@Test
//	public void test_12_CompanyFacade_removeCoupon() throws CouponDoesNotExistsException, CompanyNotExistException, CompanyAllreadyExistsException, CouponAllreadyExistsException, InterruptedException  
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     
//		try {
//			Coupon coupon = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
//			coupon.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon);
//			ArrayList<Coupon> couponList = (ArrayList<Coupon>) couponRepo.findAll();
//			Assert.assertEquals(1, couponList.size());
//			Coupon couponFromDB = companyFacade.getCoupon(coupon.getId());
//			companyFacade.removeCoupon(coupon);
//			couponList = (ArrayList<Coupon>) couponRepo.findAll();
//			Assert.assertEquals(0, couponList.size() );;
//			
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();}
//	}
////		@Test
////		public void test_13_CompanyFacade_updateCoupon() throws CouponDoesNotExistsException, CompanyNotExistException, CompanyAllreadyExistsException, CouponAllreadyExistsException, InterruptedException  
////		{
////			     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
////			     adminFacade.createCompany(compLoggedIn2);
////			     
////			try {
////				Coupon coupon = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
////				coupon.setCompany(compLoggedIn2); 
////				companyFacade.createCoupon(coupon);
////				Coupon coupFromDB = companyFacade.getCoupon(coupon.getId());
////				Date newEndDate = DateMaker.fixDate(2020, 4, 15);
////				coupFromDB.setEndDate(newEndDate);
////				coupFromDB.setPrice(10);
////				companyFacade.updateCoupon(coupFromDB);
////				Assert.assertNotEquals(coupon.getPrice(), coupFromDB.getPrice());
////				Assert.assertNotEquals(coupon.getEndDate(), coupFromDB.getEndDate());
////				
////				
////			}
////		catch (CouponDoesNotExistsException e) {
////			e.printStackTrace();
////		}
////	}
//	@Test
//	public void test_14_CompanyFacade_getAllCoupons() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
//			Coupon coupon2 = new Coupon("Burger King 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "whopper");
//			Coupon coupon3 = new Coupon("Wendey's 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "fries");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			ArrayList<Coupon> couponsList = (ArrayList<Coupon>) companyFacade.getAllCoupons();
//			Assert.assertEquals(3, couponsList.size());
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//}
//	@Test
//	public void test_15_CompanyFacade_getACoupon() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     
//		try {
//			Coupon coupon = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
//			coupon.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon);
//			Coupon coupFromDB = companyFacade.getCoupon(coupon.getId());
//			Assert.assertEquals(coupon.getImage(), coupFromDB.getImage());
//			Assert.assertEquals(coupon.getTitle(), coupFromDB.getTitle());
//			Assert.assertEquals(coupon.getAmount(), coupFromDB.getAmount());
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//}
//	@Test
//	public void test_15_CompanyFacade_getCouponsByType() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
//			Coupon coupon2 = new Coupon("BurgerKing", companyFacade.startDate, companyFacade.endDate, 1, CouponType.FOOD, "supersize", 9 , "whopper");
//			Coupon coupon3 = new Coupon("Samsung", companyFacade.startDate, companyFacade.endDate, 1, CouponType.ELECTRICAPPLIANCE, "fridge sale", 1000 , "fridge");
//			Coupon coupon4 = new Coupon("Amcor" , companyFacade.startDate, companyFacade.endDate, 1, CouponType.ELECTRICAPPLIANCE, "fridge sale", 900 , "fridge");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			coupon4.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			companyFacade.createCoupon(coupon4);
//			//ArrayList<Coupon> couponListByType = (ArrayList<Coupon>) companyFacade.getCouponsByType(CouponType.FOOD, compLoggedIn2.getId());
//			//ArrayList<Coupon> couponListByType2 = (ArrayList<Coupon>) companyFacade.getCouponsByType(CouponType.ELECTRICAPPLIANCE, compLoggedIn2.getId());
//			//Assert.assertEquals(2, couponListByType.size());
//			//Assert.assertEquals(2, couponListByType2.size());
//			
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//}
//	@Test
//	public void test_16_CompanyFacade_getCouponsByDate() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     Date endDate = new Date();
//		     Date endDate1 = new Date();
//		     Date testDate = new Date();
//		     Date today = new Date();
//		     endDate = DateMaker.fixDate(2018, 12, 20);
//		     endDate1 = DateMaker.fixDate(2018, 6, 20);
//		     testDate = DateMaker.fixDate(2018, 9, 20);
//
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", today, endDate1, 1, CouponType.FOOD, "supersize", 9 , "bigmac");
//			Coupon coupon2 = new Coupon("BurgerKing", today, endDate, 1, CouponType.FOOD, "supersize", 9 , "whopper");
//			Coupon coupon3 = new Coupon("Samsung", today, endDate, 1, CouponType.ELECTRICAPPLIANCE, "fridge sale", 1000 , "fridge");
//			Coupon coupon4 = new Coupon("Amcor" , today, endDate, 1, CouponType.ELECTRICAPPLIANCE, "fridge sale", 900 , "fridge");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			coupon4.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			companyFacade.createCoupon(coupon4);
//			ArrayList<Coupon> couponListByDate = (ArrayList<Coupon>) companyFacade.getCouponsByDate(testDate, compLoggedIn2.getId());
//			Assert.assertEquals(1, couponListByDate.size());
//			
//			
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//}
//	@Test
//	public void test_16_CompanyFacade_getCouponsByPrice() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     Date endDate = new Date();
//		     Date today = new Date();
//
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 5 , "bigmac");
//			Coupon coupon2 = new Coupon("BurgerKing", today, endDate, 1, CouponType.FOOD, "supersize", 9 , "whopper");
//			Coupon coupon3 = new Coupon("Samsung", today, endDate, 1, CouponType.ELECTRICAPPLIANCE, "fridge sale", 1000 , "fridge");
//			Coupon coupon4 = new Coupon("Amcor" , today, endDate, 1, CouponType.ELECTRICAPPLIANCE, "fridge sale", 900 , "fridge");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			coupon4.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			companyFacade.createCoupon(coupon4);
//			ArrayList<Coupon> couponListByPrice = (ArrayList<Coupon>) companyFacade.getCouponsByPrice(950, compLoggedIn2.getId());
//			Assert.assertEquals(3, couponListByPrice.size());
//			
//			
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//}
//	@Test
//	public void test_17_CustomerFacade_purchaseCoupon() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, CustomerAllreadyExistsException, CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     Customer custLoggedIn2 = new Customer("testCustomer", "5678");
//		     adminFacade.createCustomer(custLoggedIn2);
//		     Date endDate = DateMaker.fixDate(2030, 5, 5);
//		     Date today = new Date();
//		     ArrayList<Coupon> customerCouponList = new ArrayList<>();
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 5 , "bigmac");
//			coupon1.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			customerFacade.purchaseCoupon(coupon1, custLoggedIn2);
//			customerCouponList = couponRepo.findCouponByCustomersId(custLoggedIn2.getId());
//			Assert.assertEquals(1, customerCouponList.size());
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();}
//	}
//	
//	@Test
//	public void test_18_CustomerFacade_getAllPurchasedCoupons() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, CustomerAllreadyExistsException, CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     Customer custLoggedIn2 = new Customer("testCustomer", "5678");
//		     adminFacade.createCustomer(custLoggedIn2);
//		     Date endDate = DateMaker.fixDate(2030, 5, 5);
//		     Date today = new Date();
//		     ArrayList<Coupon> customerCouponList = new ArrayList<>();
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 5 , "bigmac");
//			Coupon coupon2 = new Coupon("Burger King 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 5 , "Whopper");
//			Coupon coupon3 = new Coupon("Wendeys 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 5 , "Square");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			customerFacade.purchaseCoupon(coupon1, custLoggedIn2);
//			customerFacade.purchaseCoupon(coupon2, custLoggedIn2);
//			customerFacade.purchaseCoupon(coupon3, custLoggedIn2);
//			customerCouponList = couponRepo.findCouponByCustomersId(custLoggedIn2.getId());
//			Assert.assertEquals(3, customerCouponList.size());
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//
//	}
//
//	@Test
//	public void test_19_getAllPurchasedCouponsByType() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, CustomerAllreadyExistsException, CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     Customer custLoggedIn2 = new Customer("testCustomer", "5678");
//		     adminFacade.createCustomer(custLoggedIn2);
//		     Date endDate = DateMaker.fixDate(2030, 5, 5);
//		     Date today = new Date();
//		     Collection<Coupon> customerCouponListByType = new ArrayList<>();
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 5 , "bigmac");
//			Coupon coupon2 = new Coupon("Booking.com", today, endDate, 1, CouponType.LEISURE, "Hotel", 5 , "ocean");
//			Coupon coupon3 = new Coupon("Expedia", today, endDate, 1, CouponType.LEISURE, "Hotel", 5 , "Beach");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			customerFacade.purchaseCoupon(coupon1, custLoggedIn2);
//			customerFacade.purchaseCoupon(coupon2, custLoggedIn2);
//			customerFacade.purchaseCoupon(coupon3, custLoggedIn2);
//			customerCouponListByType = couponRepo.findbytypeAndCustomerId(CouponType.LEISURE, custLoggedIn2.getId());
//			Assert.assertEquals(2, customerCouponListByType.size());
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//
//	}
//
//	@Test
//	public void test_20_getAllPurchasedCouponsByPrice() 
//				throws CouponDoesNotExistsException, CompanyNotExistException, 
//					CouponAllreadyExistsException, CompanyAllreadyExistsException, CouponsNullListException, CustomerAllreadyExistsException, CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, InterruptedException   
//	{
//		     Company compLoggedIn2 = new Company("Mcdonalds", "8901", "Mcdonalds@gmail.com");
//		     adminFacade.createCompany(compLoggedIn2);
//		     Customer custLoggedIn2 = new Customer("testCustomer", "5678");
//		     adminFacade.createCustomer(custLoggedIn2);
//		     Date endDate = DateMaker.fixDate(2030, 5, 5);
//		     Date today = new Date();
//		     Collection<Coupon> customerCouponListByType = new ArrayList<>();
//		try {
//			Coupon coupon1 = new Coupon("McDonalds 1.90", today, endDate, 1, CouponType.FOOD, "supersize", 50 , "bigmac");
//			Coupon coupon2 = new Coupon("Booking.com", today, endDate, 1, CouponType.LEISURE, "Hotel", 500 , "ocean");
//			Coupon coupon3 = new Coupon("Expedia", today, endDate, 1, CouponType.LEISURE, "Hotel", 250 , "Beach");
//			coupon1.setCompany(compLoggedIn2); 
//			coupon2.setCompany(compLoggedIn2); 
//			coupon3.setCompany(compLoggedIn2); 
//			companyFacade.createCoupon(coupon1);
//			companyFacade.createCoupon(coupon2);
//			companyFacade.createCoupon(coupon3);
//			customerFacade.purchaseCoupon(coupon1, custLoggedIn2);
//			customerFacade.purchaseCoupon(coupon2, custLoggedIn2);
//			customerFacade.purchaseCoupon(coupon3, custLoggedIn2);
//			customerCouponListByType = couponRepo.findbyPriceAndCustomerId(300, custLoggedIn2.getId());
//			Assert.assertEquals(2, customerCouponListByType.size());
//		}
//	catch (CouponAllreadyExistsException e) {
//		e.printStackTrace();
//	}
//	}
//	
//	
//	}
//	
//
//		
//		
//		
//		
//		
//		
//		
//	
