package com.example.demo.webServices;

import java.util.Collection;




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
	@Autowired AdminFacade af;
	
		private AdminFacade getFacade()
		{
			AdminFacade af = (AdminFacade) cs.login("admin", "1234", ClientType.ADMIN);
			return af;
			
		}
		@RequestMapping (value = "/AdminWS/createCompany", method = RequestMethod.POST)
		public void createCompany (@RequestBody Company c) throws CompanyAllreadyExistsException, InterruptedException 
		{
			AdminFacade af = getFacade();
			  af.createCompany(c);
		}
		@RequestMapping (value = "/AdminWS/removeCompany/{comptoremove}", method = RequestMethod.DELETE)
		public void removeCompany (@PathVariable ("comptoremove") String c) throws CompanyAllreadyExistsException, InterruptedException, CompanyNotExistException 
		{
			Company remove = new Company();
			remove = cr.findByCompName(c);
			AdminFacade af = getFacade();
			af.removeCompany(remove);
		}
		@RequestMapping (value = "/AdminWS/updateCompany", method = RequestMethod.PUT)
		public void updateCompany (@RequestBody Company c) throws InterruptedException, CompanyNotExistException 
		{
			AdminFacade af = getFacade();
			  af.updateCompany(c);
		}
		@RequestMapping (value = "/AdminWS/getCompany/{compName}", method = RequestMethod.GET)
		public Company getCompany (@PathVariable String compName) throws CompanyNotExistException, InterruptedException      
		{	
			AdminFacade af = getFacade();
			 return af.getCompany(compName);
		}
		@RequestMapping (value = "/AdminWS/getAllCompanies", method = RequestMethod.GET)
		public Collection<Company> getAllCompanies() throws CompaniesNullListException, InterruptedException
		{
			AdminFacade af = getFacade();
			return af.getAllCompanies();
		}
		
		
		@RequestMapping (value = "/AdminWS/createCustomer", method = RequestMethod.POST)
		public void createCustomer (@RequestBody Customer customer) throws CustomerAllreadyExistsException, InterruptedException     
		{
			AdminFacade af = getFacade();
			  af.createCustomer(customer);
		}
		@RequestMapping (value = "/AdminWS/removeCustomer/{customertoremove}", method = RequestMethod.DELETE)
		public void removeCustomer (@PathVariable ("customertoremove") String c) throws CustomerDoesNotExistException, InterruptedException  
		{
			AdminFacade af = getFacade();
			  af.removeCustomer(c);
		}
		
		
		@RequestMapping (value = "/AdminWS/updateCustomer", method = RequestMethod.PUT)
		public void updateCustomer (@RequestBody Customer customer) throws InterruptedException, CustomerDoesNotExistException     
		{
			AdminFacade af = getFacade();
			  af.updateCustomer(customer);
		}
		@RequestMapping (value = "/AdminWS/getCustomer/{id}", method = RequestMethod.GET)
		public Customer getCustomer(@PathVariable ("id") long id) throws InterruptedException
		{
			AdminFacade af = getFacade();
			return af.getCustomer(id);
			
		}
		
		@RequestMapping (value = "/AdminWS/getAllCustomers", method = RequestMethod.GET)
		public Collection<Customer> getAllCustomers() throws InterruptedException
		{
			AdminFacade af = getFacade();
			return af.getAllCustomers();
			
		}
		
		
		
		
}

		

