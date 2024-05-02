package com.spring.angular.records;

import java.util.Date;
import java.util.TimerTask;

public class MaTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		    System.out.println(new Date() + " Executions de ma tache");
		  
	}

}
