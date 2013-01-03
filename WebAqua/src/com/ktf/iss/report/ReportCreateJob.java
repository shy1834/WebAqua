/*
 * Created on 2005. 1. 21.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.iss.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;

/**
 * @author hyunyun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportCreateJob implements Runnable {
	private Log log = LogFactory.getLog(this.getClass());
	private int state = 0;
	private final static int RUNNING = 1;
	private final static int TERMINATED = 0;
	private static ReportCreateJob reportCreateJob = null;
	private int startHour, startMinute;

	private ReportCreateJob(int startHour, int startMinute) {
		super();
		this.startHour = startHour;
		this.startMinute = startMinute;
		state = RUNNING; 
		new Thread(this).start();
	}

	public static ReportCreateJob getInstance(int startHour, int startMinute) {
		if(reportCreateJob == null)
			reportCreateJob = new ReportCreateJob(startHour, startMinute);
		return reportCreateJob;
	}

	public static ReportCreateJob getInstance() {
		return reportCreateJob;
	}

	public void run() {
		try {
			int lastDay = -1;
			while(state == RUNNING) {
				synchronized(this) {
					wait(1000*60);
					Calendar cal = Calendar.getInstance();
					int day = cal.get(Calendar.DAY_OF_YEAR);
					if(cal.get(Calendar.HOUR_OF_DAY) >= startHour && cal.get(Calendar.MINUTE) >= startMinute
						&& (day > lastDay || (day == 1 && lastDay == 365)) ) {
						int mode = 1;
						if(cal.getFirstDayOfWeek() == Calendar.SUNDAY)
							mode = 2;
						if(cal.get(Calendar.DAY_OF_MONTH) == 1)
							mode = 3;
						Report_BO bo = Report_BO.getInstance();
						bo.createReportAuto(mode);
						lastDay = day;
					}
				}
			}
		} catch (Exception e) {}
	}
	
	public void stop() {
		synchronized(this) {
			state = TERMINATED;
			notify();
		}
	}	

	public static void main(String[] args) {
	}
}
