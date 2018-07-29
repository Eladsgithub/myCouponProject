package com.example.demo.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Customer")
@XmlRootElement
public class Customer implements Serializable{

	@Id   
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // PK
	
	@Column
	private String custName;
	
	@Column
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "customers_coupons",
				joinColumns = @JoinColumn(name = "customer_id"),
				inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private List<Coupon> coupons = new ArrayList<>();
	
	/***
	 * Empty CTR
	 */
	public Customer() {
	}
	
	
	/***
	 * CTR using fields for create new object
	 * @param custName
	 * @param password
	 */
	public Customer(String custName, String password) {
		super();
		this.custName = custName;
		this.password = password;
	}


	// Getters & Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	public void addCoupon(Coupon coupon){
		this.coupons.add(coupon);
	}

	// toString
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password 
				+ "]";
	}
	
		

}