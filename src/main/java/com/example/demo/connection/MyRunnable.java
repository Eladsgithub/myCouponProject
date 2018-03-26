package com.example.demo.connection;

import org.springframework.context.ApplicationContext;

import com.example.demo.DBDAO.CouponDBDAO;

public class MyRunnable implements Runnable {

	private ApplicationContext ctx;
	
    public MyRunnable(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@Override
    public void run() {


        try {

        	CouponDBDAO couponDBDAO  = ctx.getBean(CouponDBDAO.class);
   
        	
            //DBConnection dbCOnnection = ConnectionPool.getInstance().getConnection();
            
            Thread.sleep(86400000);
            //ConnectionPool.getInstance().returnConenction( dbCOnnection );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
