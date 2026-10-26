package part01;

import java.util.ArrayList;

public class Exhibit {
	private int id;
	private String name;
	private String description;

	private ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
	
	
	public Exhibit(int id, String name, String description, ArrayList<Artifact> artifacts) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.artifacts = artifacts;
	}
	
	public Exhibit() {
		//Blank constructor for initialising
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String value) {
		name = value;
	}
	
	public void setDescription(String value) {
		description = value;
	}
	
	public void setArtifacts(ArrayList<Artifact> artifacts) {
		this.artifacts = artifacts;
	}
	
	public ArrayList<Artifact> getArtifacts(){
		return artifacts;
	}
	
	public String returnInfo(int engagementTime) {
		String artifactsList = "\nExhibit artifacts:";
		if(artifacts.size() > 0) {
			for(int i = 0; i < artifacts.size(); i++) {
				artifactsList = artifactsList + "\n" + artifacts.get(i).toString();
			}
		}
		
		String info;
		if(!artifactsList.isBlank())
		{
			info = "Id:" + id + "\nName:" + name + "\nDescription:" + description + artifactsList + "\nEngagement Time:" + engagementTime;
		}
		else {
			info = "Id:" + id + "\nName:" + name + "\nDescription:" + description + "\nEngagement Time:" + engagementTime;
		}
		
		return info;
	}
}


