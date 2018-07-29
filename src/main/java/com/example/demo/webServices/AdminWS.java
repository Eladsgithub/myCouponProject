package com.example.demo.webServices;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DBDAO.CompanyRepo;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.Customer;
import com.example.demo.Facades.AdminFacade;
import com.example.demo.Facades.CustomerFacade;
import com.example.demo.common.ClientType;
import com.example.demo.entry.CouponSystem;
import com.example.demo.exceptions.CompaniesNullListException;
import com.example.demo.exceptions.CompanyAllreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CustomerAllreadyExistsException;
import com.example.demo.exceptions.CustomerDoesNotExistException;


@CrossOrigin("*")
@RestController
public class AdminWS {

	@Autowired CouponSystem cs;
	@Autowired CompanyRepo cr;
	
		private AdminFacade getFacade(HttpServletRequest req)
		{
			AdminFacade af = (AdminFacade) req.getSession().getAttribute("adminFacade");
			return af;
			
		}
		@RequestMapping (value = "/AdminWS/createCompany", method = RequestMethod.POST)
		public void createCompany (HttpServletRequest req,@RequestBody Company c) throws CompanyAllreadyExistsException, InterruptedException 
		{
			AdminFacade af = getFacade(req);
			  af.createCompany(c);
		}
		@RequestMapping (value = "/AdminWS/removeCompany/{comptoremove}", method = RequestMethod.DELETE)
		public void removeCompany (HttpServletRequest req,@PathVariable ("comptoremove") String c) throws CompanyAllreadyExistsException, InterruptedException, CompanyNotExistException 
		{
			Company remove = new Company();
			remove = cr.findByCompName(c);
			AdminFacade af = getFacade(req);
			af.removeCompany(remove);
		}
		@RequestMapping (value = "/AdminWS/updateCompany", method = RequestMethod.PUT)
		public void updateCompany (HttpServletRequest req,@RequestBody Company c) throws InterruptedException, CompanyNotExistException 
		{
			AdminFacade af = getFacade(req);
			  af.updateCompany(c);
		}
		@RequestMapping (value = "/AdminWS/getCompany/{compName}", method = RequestMethod.GET)
		public Company getCompany (HttpServletRequest req,@PathVariable String compName) throws CompanyNotExistException, InterruptedException      
		{	
			AdminFacade af = getFacade(req);
			 return af.getCompany(compName);
		}
		@RequestMapping (value = "/AdminWS/getAllCompanies", method = RequestMethod.GET)
		public Collection<Company> getAllCompanies(HttpServletRequest req) throws CompaniesNullListException, InterruptedException
		{
			AdminFacade af = getFacade(req);
			return af.getAllCompanies();
		}
		
		
		@RequestMapping (value = "/AdminWS/createCustomer", method = RequestMethod.POST)
		public void createCustomer (HttpServletRequest req,@RequestBody Customer customer) throws CustomerAllreadyExistsException, InterruptedException     
		{
			AdminFacade af = getFacade(req);
			  af.createCustomer(customer);
		}
		@RequestMapping (value = "/AdminWS/removeCustomer/{customertoremove}", method = RequestMethod.DELETE)
		public void removeCustomer (HttpServletRequest req,@PathVariable ("customertoremove") String c) throws CustomerDoesNotExistException, InterruptedException  
		{
			AdminFacade af = getFacade(req);
			  af.removeCustomer(c);
		}
		
		
		@RequestMapping (value = "/AdminWS/updateCustomer", method = RequestMethod.PUT)
		public void updateCustomer (HttpServletRequest req,@RequestBody Customer customer) throws InterruptedException, CustomerDoesNotExistException     
		{
			AdminFacade af = getFacade(req);
			  af.updateCustomer(customer);
		}
		@RequestMapping (value = "/AdminWS/getCustomer/{id}", method = RequestMethod.GET)
		public Customer getCustomer(HttpServletRequest req,@PathVariable ("id") long id) throws InterruptedException
		{
			AdminFacade af = getFacade(req);
			return af.getCustomer(id);
			
		}
		
		@RequestMapping (value = "/AdminWS/getAllCustomers", method = RequestMethod.GET)
		public Collection<Customer> getAllCustomers(HttpServletRequest req) throws InterruptedException
		{
			AdminFacade af = getFacade(req);
			return af.getAllCustomers();
			
		}
		
		
		
		
}

		

