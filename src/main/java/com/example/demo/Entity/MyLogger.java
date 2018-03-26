package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

@Entity(name = "MyLogger")
public class MyLogger {

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String action;
	
	@Column
	private Date date;
	
	
	public MyLogger() {
	}
	
	public MyLogger(Date date, String action) {
		super();
		this.date = date;
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "MyLogger [id=" + id + ", date=" + date + ", action=" + action + "]";
	}
	
}
