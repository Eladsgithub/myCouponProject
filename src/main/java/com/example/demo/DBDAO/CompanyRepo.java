package com.example.demo.DBDAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Company;
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
	boolean findByCompNameAndPassword(String compName, String password);
	
	

	


}
