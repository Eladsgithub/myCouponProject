package com.example.demo.webServices;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Facades.AdminFacade;
import com.example.demo.Facades.CompanyFacade;
import com.example.demo.Facades.CustomerFacade;
import com.example.demo.common.ClientType;
import com.example.demo.entry.CouponSystem;

@Controller
@CrossOrigin("*")
public class LoginServlet {
	
	@Autowired
	private CouponSystem couponSystem;
	
	
	@RequestMapping( value = "/login", 
			method = RequestMethod.POST)
	public String login( HttpServletRequest request,@RequestParam String username,@RequestParam String password , @RequestParam String type) 
	{
			ClientType clientType = ClientType.valueOf(type);
			switch (clientType)
			{
				case ADMIN:
				AdminFacade adminFacade = (AdminFacade) couponSystem.login(username, password, clientType);
				request.getSession().setAttribute("adminFacade", adminFacade);
				return "redirect:http://localhost:8080/admin/index.html";
				//return "redirect:http://localhost:4200";
					
				case COMPANY:
					CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(username, password, clientType);
					request.getSession().setAttribute("companyFacade", companyFacade);
					return "redirect:http://localhost:8080/company/index.html";
					//return "redirect:http://localhost:4200";
						
				case CUSTOMER:
					CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(username, password, clientType);
					request.getSession().setAttribute("customerFacade", customerFacade);
					return "redirect:http://localhost:8080/customer/index.html";
				//return "redirect:http://localhost:4200";		
			}
			return null;
			
	}

}