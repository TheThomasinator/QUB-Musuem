package part01;

import java.util.ArrayList;

public class AnnualPlan {
	private int id;
	private int year;
	
	public AnnualPlan(int id, int year) {
		this.id = id;
		this.year = year;
	}
	
	public AnnualPlan() {
		//Blank constructor for initialising
	}
	
	public int getId() {
		return id;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
}
