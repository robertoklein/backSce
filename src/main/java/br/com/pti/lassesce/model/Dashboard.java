package br.com.pti.lassesce.model;

public class Dashboard {

	private int lateLoans;
	
	private int dayRefound; 
	
	private int totalSchedules;
	
	private int openLoans;
	
	private int dayPullout;

	public int getDayPullout() {
		return dayPullout;
	}

	public void setDayPullout(int dayPullout) {
		this.dayPullout = dayPullout;
	}

	public int getLateLoans() {
		return lateLoans;
	}

	public void setLateLoans(int lateLoans) {
		this.lateLoans = lateLoans;
	}

	public int getDayRefound() {
		return dayRefound;
	}

	public void setDayRefound(int dayRefound) {
		this.dayRefound = dayRefound;
	}

	public int getTotalSchedules() {
		return totalSchedules;
	}

	public void setTotalSchedules(int totalSchedules) {
		this.totalSchedules = totalSchedules;
	}

	public int getOpenLoans() {
		return openLoans;
	}

	public void setOpenLoans(int openLoans) {
		this.openLoans = openLoans;
	}
	
	
}
