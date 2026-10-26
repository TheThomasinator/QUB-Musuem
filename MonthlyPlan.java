package part01;

import java.util.ArrayList;

public class MonthlyPlan {
	public int planId;
	public String hall;
	public int month;
	public Exhibit monthExhibit = new Exhibit();
	
	public MonthlyPlan(int planId, int month, String hall) {
		this.planId = planId;
		this.month = month;
		this.hall = hall;
	}
	
	public int getPlanId() {
		return planId;
	}
	
	public int getMonth() {
		return month;
	}
	
	public Exhibit getExhibit(){
		return monthExhibit;
	}
	
	public String getHall() {
		return hall;
	}
	
	public void setExhibit(Exhibit exhibit) {
		monthExhibit = exhibit;
	}
}


