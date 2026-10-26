package part01;

public enum MenuState {

	
	//Main Menus
	MAINMENU("\n1. Artifact Menu\n2. Exhibit Menu\n3. Annual Plan Menu\n4. Exit \nSelect between options 1-4"), 
	ARTIFACTMENU("\n1. Artifact Management\n2. Search Artifacts\n3. Return to Main Menu\nSelect between options 1-3"), 
	EXHIBITMENU("\n1. Exhibit Management\n2. Search Exhibit \n3. Manage Exhibit Artifacts\n4. Return to Main Menu\nSelect between options 1-4	"), 
	PLANMENU("\n1. Add Annual Plan\n2. Edit Annual Plan\n3. Delete Annual Plan\n4. Add an Exhibit to a plan\n4. Search for a plan\n5. Get an annual plan\n6. Main Menu"),
	
	//Artifact Menus
	ARTIFACTAED("\n1. Add Artifact\n2. Edit Artifact\n3. Delete Artifact\n4. Return to Artifact Menu \nSelect between options 1-4"), 
	ADDARTIFACT("\nAdd Artifact name"),
	EDITARTIFACT("\nEnter the ID of the artifact you would like to edit"), 
	DELETEARTIFACT("\nEnter the ID of the artifact you would like to delete"), 
	SEARCHARTIFACT("\n1. Search Artifact by criteria\n2. Get all Artifacts\n3. Get engagement time by ID\n4. Return to Artifact Menu"),
	DISPLAYALLARTIFACTS("\nWhat criteria would you like to sort by\n1. ID\n2. Name\n3. Type\n4. Engagement Time\nSelect an option 1-4"),
	SEARCHARTIFACTBYCRITERIA("\nWhat criteria would you like to search by\n1. ID\n2. Name\n3. Name Contains\n4. Type\n5. Engagement Time\nSelect an option 1-5"),
	GETARTIFACTENGAGEMENTTIME("\nEnter the ID of the artifact you want to know the engagement time of"),
	
	//Exhibit Menus
	EXHIBITAED("\n1. Add Exhibit\n2. Edit Exhibit\n3. Delete Artifact\n4. Return to Exhibit Menu"), 
	ADDEXHIBIT("\nAdd Exhibit name"),
	EDITEXHIBIT("\nWhat is the ID of the exhibit you would like to edit"),
	DELETEEXHIBIT("\nWhat is the ID of the exhibit you would like to delete"),
	
	SEARCHEXHIBIT("\n1. Search Exhibit by criteria\n2. Get all exhibits\n3. Get engagement time by ID\n4. Get exhibit artifacts\n5. Return to Exhibit Menu"),
	SEARCHEXHIBITBYCRITERIA("\nWhat criteria would you like to search by\n1. ID\n2. Name\n3. Type\n4. Engagement Time\nSelect an option 1-4"),
	DISPLAYALLEXHIBITS("\nWhat critera would you like to sort by\n1. Id\n2. Name\n3. Name Contains\n4. Description"),
	GETEXHIBITENGAGEMENTTIME("\nWhat is the ID of the exhibit you want to get the engagement time for"),
	GETEXHIBITARTIFACTS("\nWhat is the ID of the exhibit you want to get the artifacts for"),
	
	MANAGEEXHIBITARTIFACTS("\n1. Add Artifact to an exhibit\n2. Remove an artifact from an exhibit\n3. Re-order artifacts in an exhibit\n4. Manage exhibit signs\n5. Return to Exhibit menu"),
	ADDEXHIBITARTIFACT("\nWhat is the id of the exhibit you would like to add an artifact to"),
	DELETEEXHIBITARTIFACT("\nWhat is the id of the exhibit you would like to remove an artifact from"),
	REORDEREXHIBITARTIFACT("\nWhat is the id of the exhibit you would like to re-order"),
	
	MANAGEEXHIBITSIGN("\n1. Get an Exhibit sign\n2. Change an exhibit sign\n3. Return to Manage Exhibit Artifacts"),
	GETEXHIBITSIGN("\nWhat Exhibit is the sign you want in?"), 
	CHANGEEXHIBITSIGN("\nWhat Exhibit is the sign you want to change?"),
	
	//Annual Plans
	ADDPLAN("\nWhat year is the plan for?"),
	EDITPLAN("\nWhat is the ID of the plan you want to edit?"),
	DELETEPLAN("\nWhat is the ID of the plan you want to delete?"),
	ADDPLANEXHIBIT("\nWhat is the ID of the plan you want to add an exhibit to?"),
	SEARCHFOREXHIBITINPLAN("\nWhat is the id of the plan?"),
	GETANNUALPLAN("\nWhat is the id of the plan?");
	
	private String menuString;
	
	private MenuState(String state) {
		menuString = state;
	}
	
	public String toString() {
		return menuString;
	}

	
}
