package com.example.demo.common;

import java.util.Calendar;
import java.util.Date;

/***
 * Date Maker
 * @author Elad Cohen
 *
 */
public class DateMaker {

	
		
	/***
	 * Providing date by parameters
	 * @param year
	 * @param month
	 * @param day
	 * @return Date 
	 */
	public static Date fixDate(int year, int month, int day)
	{
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		date = cal.getTime();
		
		return date;
	}
	
}
