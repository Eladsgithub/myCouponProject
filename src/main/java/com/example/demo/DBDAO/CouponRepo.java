package com.example.demo.DBDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Coupon;
import com.example.demo.common.CouponType;
/**
 * 
 * @author Elad Cohen
 *
 */
public interface CouponRepo extends CrudRepository<Coupon, Long>{

	Coupon findById(long id);
	
	Coupon findByTitle(String title);
//	compiles a list of coupons by type 
	ArrayList<Coupon> findByType(CouponType couponType);
	
//	compiles a list of coupons by type and company id 
	ArrayList<Coupon> findByTypeAndCompany_id(CouponType couponType, long compLoggedInId);

	/**
	 * query to remove coupon after deletion
	 * @param couponId
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon coup WHERE coup.id = :couponId AND coup.company.id = :companyId")
	void removeCoupon(@Param("couponId") long couponId, @Param("companyId") long companyId);
		
	/**
	 * query to remove coupon after expired - used by the daily thread
	 * @param couponId
	 * @param companyId
	 */
	@Modifying		
	@Transactional
	@Query("DELETE FROM Coupon coup WHERE coup.endDate<:endDate")
	void deleteExpiredCoupons(@Param("endDate") Date today);
	
	
//	compiles a list of coupons by customer id 
	Collection<Coupon> findByCustomersId(long customerId);
	
	/**
	 * query that compiles a list coupons which price is lower then given price from Coupon table 
	 * and company id from Company table - manyToOne association
	 * @param price
	 * @param compLoggedInId
	 * @return
	 */
	@Modifying		
	@Transactional
	@Query("SELECT c FROM Coupon c WHERE c.price < :price AND company_id= :companyId")
	Collection<Coupon> findWherePriceLowerThan(@Param("price")double price,@Param ("companyId") long compLoggedInId);
	
	/**
	 * query that compiles a list coupons which endDate is earlier then given endDate from Coupon table 
	 * and company id from Company table - manyToOne association
	 * @param endDate
	 * @param comapnyId
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.endDate < :endDate AND company_id= :companyId")
	Collection<Coupon> findWhereEndDateLowerThan(@Param("endDate")Date endDate ,@Param ("companyId") long comapnyId);

//	@Query("SELECT cp FROM CUSTOMER cust JOIN cust.coupons AS cp WHERE cust.id=:id AND cp.type=:couponType")
//	Collection<Coupon> findAllPurchasedCouponsType(@Param("id") long custId, @Param("couponType") CouponType type);
	
	/**
	 * query that compiles a list coupons which type is matches then given type from Coupon table 
	 * and company id from Company table - manyToOne association
	 * @param type
	 * @param customerId
	 * @return
	 */
	 @Transactional
	 @Modifying
	 @Query("SELECT coup FROM Coupon coup WHERE coup.type = :type AND coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	 ArrayList<Coupon> findbytypeAndCustomerId(@Param("type")CouponType type, @Param("customerId")long customerId);
	 
//	Coupon findCouponByTitle(String title);
	
	Coupon findByIdAndCustomersId(long couponId, long customerId);

	ArrayList<Coupon> findCouponByCustomersId(long id);
/**
 * query that compiles a list coupons which price is lesser then given price from Coupon table 
	 * and customer id from Customer table - manyToMany association
 * @param price
 * @param customerId
 * @return
 */
	 @Transactional
	 @Modifying
	 @Query("SELECT coup FROM Coupon coup WHERE coup.price < :price AND coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	 Collection<Coupon> findbyPriceAndCustomerId(@Param("price")double price, @Param("customerId")long customerId);


	
	

}
