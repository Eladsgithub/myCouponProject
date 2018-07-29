package com.example.demo.DBDAO;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.Customer;
import com.example.demo.common.CouponType;



public interface CustomerRepo extends CrudRepository<Customer, Long>{

	Customer findById(long id);

	Customer findByCustNameAndPassword(String custName, String password);
	
	Customer findByCustName(String custName);
	
//	@Query("SELECT coup FROM Coupon coup WHERE coup.id = :c.getId AND coup.id IN (SELECT coup.id FROM coup.customers c WHERE c.id = :customerId)") 
//	Coupon findCustomerCoupon(@Param("customerId") int customerId, @Param("couponId") int couponId);
//	
//	@Query("SELECT coup FROM Customer cust JOIN customer_coupon AS coup WHERE cust.id=:id AND coup.title=:title")
//	Coupon findCouponInCustomerDB(@Param("id") long customerId, @Param("title") String couponTitle);
	
	@Query("SELECT COUP FROM Customer AS CUST INNER JOIN CUST.coupons AS COUP WHERE CUST.id = :cust_id")
	List<Coupon> getAllPurchasedCouponByCustomer(@Param("cust_id")long cust_id);

	
	@Query("SELECT COUP FROM Customer AS CUST INNER JOIN CUST.coupons AS COUP WHERE CUST.id = :cust_id AND COUP.type = :type")
	List<Coupon> getAllPurchasedCouponByType(@Param("cust_id")long cust_id,@Param("type") CouponType type);

	/**
	 * Get a list of all purchased coupon below a specific price for a specific customer id.
	 * @param cust_id The customer name.
	 * @param price The price
	 * @return List
	 */
	@Query("SELECT COUP FROM Customer AS CUST INNER JOIN CUST.coupons AS COUP WHERE CUST.id = :cust_id AND COUP.price <= :price")
	List<Coupon> getAllPurchasedCouponByPrice(@Param("cust_id")long cust_id,@Param("price")double price);
	
	
	
	
	
	/**
	 * query to insert values to the joined table customer_coupons
	 * in order to associate coupon to a specific customer 
	 * @param customerId
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customer_coupons (customers_id, coupons_id) VALUES (:customerId, :couponId)", nativeQuery = true) 
	void purchaseCustomerCoupon(@Param("customerId") long  customerId);
	//, @Param("couponId") long couponId
	
//	@Query ("SELECT coupon FROM COUPON coupon where coupon.id = :id " 
//			+ "AND coupon.endDate > :date " 
//			+ "AND coupon.amount > :0 " 
//			+ "AND coupon.id IN (SELECT c.id FROM c.customers cust WHERE cust.id = :customer_id) ")
//		Coupon findIfCustomerCanBuyCoupon (@Param("customer_id") long customer_id, @Param("amount") int amount,
//				@Param("endDate") Date endDate, @Param("id") long id);
	/**
	 * query to alter coupon amount by -1 after purchase in table Coupon
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coup.amount = amount-1 WHERE coup.id = :couponId")
	void updateAmount(@Param("couponId") long couponId);

//	@Query("SELECT coup FROM Customer cust JOIN customer_coupon AS coup WHERE cust.id=:id AND coup.couponType= :couponType")
//	Collection<Coupon> findByCustomerId(long customerId);
//	
//	@Query("SELECT coup FROM Coupon coup WHERE coup.type = :type "
//			+ "AND coup.id IN(SELECT coup.id FROM coup.customers cust WHERE cust.id = :customer_id))") 
//	Collection<Coupon> findCustCouponsByType(@Param("customerId") long custLoggedInId, @Param("type") CouponType type);
//
//	@Query("SELECT coup FROM Customer cust JOIN customer_coupon AS coup WHERE cust.id=:id AND coup.couponType= :couponType")
//	Collection<Coupon> findAllCouponByType(@Param("Id") long custId, @Param("couponType") CouponType type);
//	
//	@Query("SELECT coup FROM Coupon coup WHERE coup.price =< :price "
//			+ "AND coup.id IN(SELECT coup.id FROM coup.customers cust WHERE cust.id = :customer_id))") 
//	Collection<Coupon> findCustCouponsByPrice(long custLoggedInId, double price);
//
//	@Query("SELECT coup FROM Customer cust JOIN customer_coupon AS coup WHERE cust.id=:id AND coup.price < :price")
//	Collection<Coupon> findCouponByPrice(@Param("Id") long custId, @Param("price") double price);
	
}
