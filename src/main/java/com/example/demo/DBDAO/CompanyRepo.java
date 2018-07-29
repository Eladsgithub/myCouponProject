package com.example.demo.DBDAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Company;
import com.example.demo.Entity.Coupon;
import com.example.demo.common.CouponType;
/**
 * 
 * @author Elad Cohen
 * 
 */
@Repository
public interface CompanyRepo extends CrudRepository<Company, Long>{

	//finds company using company name
	Company findByCompName(String compName);
	
	//finds company using company id
	Company findById(long id);
	
	//finds company using company name and id - login purposes
	Company findByCompNameAndPassword(String compName, String password);
	
	

	@Query("SELECT COUP FROM Company AS COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id")
	List<Coupon> getAllCoupons(@Param("comp_id")long comp_id);


	@Query("SELECT coupon FROM Company COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.type = :coupon_type")
	List<Coupon> getAllCouponsByType (@Param ("company_id")long company_id , @Param("coupon_type")CouponType couponType);


	@Query("SELECT COUP FROM Company COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id AND COUP.price <= :price")
	List<Coupon> getAllCouponsByPrice(@Param("comp_id")long comp_id,@Param("price")double price);
	
	@Query("SELECT COUP FROM Company AS COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id AND COUP.endDate <= :date")
	List<Coupon> getAllCouponsEndDate(@Param("comp_id")long comp_id,@Param("date")Date date);
	
}
