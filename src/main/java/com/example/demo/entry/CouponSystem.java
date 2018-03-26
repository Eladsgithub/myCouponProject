package com.example.demo.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.Facades.AdminFacade;
import com.example.demo.Facades.CompanyFacade;
//import com.example.demo.Facades.CompanyFacade;
import com.example.demo.Facades.CouponClientFacade;
import com.example.demo.Facades.CustomerFacade;
//import com.example.demo.Facades.CustomerFacade;
//import com.example.demo.Facades.CustomerFacade;
import com.example.demo.common.ClientType;
import com.example.demo.connection.ConnectionPool;


@Component
@Scope("singleton")
public class CouponSystem {
	
	@Autowired
	AdminFacade adminFacade;
	@Autowired
	CompanyFacade companyFacade;
	@Autowired
	CustomerFacade customerFacade;
	
	
   public CouponClientFacade login(String name ,String password, ClientType clienttype){
	   switch (clienttype) {
	case ADMIN:
		 return adminFacade.login(name, password, clienttype);
	case COMPANY:
			return companyFacade.login(name, password, clienttype);
	case CUSTOMER:
			return customerFacade.login(name, password, clienttype);
	
	}
	return null;
   }
//   public void shutdown() {
//		boolean terminate = ConnectionPool.getInstance().terminateAll();
//		if (terminate) {
//			System.exit(0); 
//		}
//	  
//   }
}
 