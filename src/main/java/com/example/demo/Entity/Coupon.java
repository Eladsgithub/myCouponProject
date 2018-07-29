package com.example.demo.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.common.CouponType;

@Entity(name = "Coupon")
@XmlRootElement
public class Coupon implements Serializable{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String title;
	@Column
	private Date startDate;
	@Column
	private Date endDate;
	@Column
	private int amount;
	@Column
	@Enumerated(EnumType.STRING)
	private CouponType type;
	@Column
	private String message;
	@Column
	private double price;
	@Column
	private String image;

	
//	@ManyToOne
//	@JoinColumn(name = "Company_id", nullable = true)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private Company company;
//	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "customers_coupons",
				joinColumns = @JoinColumn(name = "coupon_id"),
				inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private List<Customer> customers = new ArrayList<>();
	
	/***
	 * Empty CTR
	 */
	public Coupon() {
	}

	/***
	 * CTR using fields for creating new object
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param message
	 * @param price
	 * @param image
	 */
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price,
			String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	// Getters & Setters
	
	
	public long getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public void setCompany(Company company) {
//		this.company = company;
//	}
//	
//
//	public Company getCompany() {
//		return company;
//	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	// toString
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}

}