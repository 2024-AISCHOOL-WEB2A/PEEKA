package com.aischool.model;

public class VacationTB {
	private int idx;
	private String empid;
	private String year;
	private double MMoffday;	// 월차
	private double YYoffday;	// 월차
	
	public VacationTB() {
		
	}
	
	public VacationTB(int idx, String empid, String year, double mMoffday, double yYoffday) {
		super();
		this.idx = idx;
		this.empid = empid;
		this.year = year;
		MMoffday = mMoffday;
		YYoffday = yYoffday;
	}
	
	
	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getEmpid() {
		return empid;
	}


	public void setEmpid(String empid) {
		this.empid = empid;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public double getMMoffday() {
		return MMoffday;
	}


	public void setMMoffday(double mMoffday) {
		MMoffday = mMoffday;
	}


	public double getYYoffday() {
		return YYoffday;
	}


	public void setYYoffday(double yYoffday) {
		YYoffday = yYoffday;
	}


	

}
