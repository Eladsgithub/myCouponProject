package com.example.demo.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


@Entity(name = "Company")
@XmlRootElement
public class Company implements Serializable{
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; // PK
	
	@Column
	private String compName;
	@Column
	private String password;
	@Column
	private String email;
	
//	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn (name = "Company_id")
	private List<Coupon> coupons = new ArrayList<>();
	
	/***
	 * Empty CTR
	 */
	public Company() {
	}

	/***
	 * CTR using fields for create new objects
	 * @param compName
	 * @param password
	 * @param email
	 */
	public Company(String compName, String password, String email) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	// Getters & Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public List<Coupon> getCoupons() {
//		return coupons;
//	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	public void addCoupon(Coupon coupon){
		this.coupons.add(coupon);
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	// toString
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}
	
	
}