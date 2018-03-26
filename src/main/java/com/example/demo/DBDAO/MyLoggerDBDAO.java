package com.example.demo.DBDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.MyLogger;

/**
 * 
 * @author Elad Cohen
 *logger will log all of the system actions 
 */
@Service
public class MyLoggerDBDAO {

	@Autowired
	private MyLoggerRepo myLoggerRepo;
	/**
	 * will order loggerRepo to save the acton data
	 * @param log
	 */
	public void logAction (MyLogger log){
		myLoggerRepo.save(log);
	}
}
