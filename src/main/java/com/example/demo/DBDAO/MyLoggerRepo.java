package com.example.demo.DBDAO;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.MyLogger;
/**
 * 
 * @author Elad Cohen
 *will manage all of the updates made to logger table
 */
public interface MyLoggerRepo extends CrudRepository<MyLogger, Long>{

}
