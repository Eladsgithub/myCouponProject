package com.example.demo.Facades;

import com.example.demo.common.ClientType;
/**
 * implemented by Facades
 * @param name
 * @param password
 * @return CouponClientFacade or null 
 */
public interface CouponClientFacade {
	
	public CouponClientFacade login(String name, String password, ClientType clinetType) ;
	
}
