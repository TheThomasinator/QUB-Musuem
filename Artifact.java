package part01;



public class Artifact {
	private int id;
	private String name;
	private String type;
	private int engagementTime;
	private String description = "blank";

	
	public Artifact(int id, String name, String type, int engagementTime, String description) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.engagementTime = engagementTime;
		this.description = description;
	}
	
	public Artifact() {
		//Blank constructor for initialising 
	}

	public String toString() {
		return "\nID: " + id + "\nName: " + name + "\nType: " + type + "\nEngagement Time: " + engagementTime + "\nDescription: " + description + "\n";
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getEngagementTime() {
		return engagementTime;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String input) {
		name = input;
	}
	
	public void setType(String input) {
		type = input;
	}
	
	public void setEngagementTime(int input) {
		engagementTime = input;
	}
	
	public void setDescription(String input) {
		description = input;
	}
}

