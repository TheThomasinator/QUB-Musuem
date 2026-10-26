package part01;

import java.util.ArrayList;
import java.util.Scanner;

import api.QUBMuseumAPI;

public class QUBMuseum implements QUBMuseumAPI {
	public MenuState currentState = MenuState.MAINMENU;
	public ArrayList<Artifact> fullArtifacts = new ArrayList<Artifact>();
	public ArrayList<Exhibit> fullExhibits = new ArrayList<Exhibit>();
	public ArrayList<ArtifactExhibitSign> fullSigns = new ArrayList<ArtifactExhibitSign>();
	public ArrayList<AnnualPlan> fullPlans = new ArrayList<AnnualPlan>();
	public ArrayList<MonthlyPlan> fullMonthlyPlans = new ArrayList<MonthlyPlan>();
	int artifactIdNumber = 1;
	int exhibitIdNumber = 1;
	int planIdNumber = 1;
	
	public static void main(String[] args) {
		// Create API which manages all business logic
		QUBMuseum api = new QUBMuseum();
		// Create UI instance
		Scanner input = new Scanner(System.in);
		// Main loop
		boolean running = true;
		while (running) {
			// Display current prompt
			System.out.println(api.getCurrentPrompt());
			// Get user input
			String userInput = input.nextLine().trim();
			// Process input through state machine
			String result = api.processInput(userInput);
			// Check for exit condition
			if ("EXIT".equals(result)) { running = false;} 
			else if (!result.isEmpty()) {
				System.out.println(result);
			}
		}
	}
	
    // --- Artifact Management ---

	@Override
	public String createArtifact(String type, String name, String description, int engagementMinutes) throws Exception {
		fullArtifacts.add(new Artifact(artifactIdNumber, name, type, engagementMinutes, description));
		artifactIdNumber++;
		currentState = MenuState.ARTIFACTAED;
		transitionTo(currentState);
		return "";
	}

	@Override
	public String getArtifactInfo(String artifactId, String infoName) throws Exception
	{
		for(int i = 0; i < fullArtifacts.size(); i++) {
			if(fullArtifacts.get(i).getId() == Integer.parseInt(artifactId)) {
				switch(infoName) {
				case "name": return fullArtifacts.get(i).getName();
				case "description": return fullArtifacts.get(i).getDescription();
				case "type": return fullArtifacts.get(i).getType();
				case "engagement": return Integer.toString(fullArtifacts.get(i).getEngagementTime());
				}
			}
		}
		return null;
	}

	@Override
	public int getArtifactEngagementTime(String artifactId) throws Exception {
		for(int i = 0; i < fullArtifacts.size(); i++) {
			if(fullArtifacts.get(i).getId() == Integer.parseInt(artifactId)){
				return fullArtifacts.get(i).getEngagementTime();
			}
		}
		return 0;
	}

	@Override
	public void updateArtifactInfo(String artifactId, String infoName, String newValue) throws Exception
	{
		for(int i = 0; i < fullArtifacts.size(); i++) {
			if(fullArtifacts.get(i).getId() == Integer.parseInt(artifactId)){
				switch(infoName) {
					case "name": fullArtifacts.get(i).setName(newValue); break;
					case "type": fullArtifacts.get(i).setType(newValue); break;
					case "engagement": fullArtifacts.get(i).setEngagementTime(Integer.parseInt(newValue)); break;
					case "description": fullArtifacts.get(i).setDescription(newValue); break;
				}
			}
		}
	}

	@Override
	public void deleteArtifact(String artifactId) throws Exception
	{
		for(int i = 0; i < fullArtifacts.size(); i++){
			if(fullArtifacts.get(i).getId() == Integer.parseInt(artifactId)) {
				fullArtifacts.remove(i);
			}
		}
	}

	@Override
	public ArrayList<String> findArtifacts(String searchCriteria, String sortBy) throws Exception
	{
		ArrayList<String> returnStrings = new ArrayList<String>();
		ArrayList<Artifact> list = new ArrayList<Artifact>();
		Scanner userInput = new Scanner(System.in);
		String search;
		
		switch(searchCriteria ) {
		default: //Returns all artifacts if no search criteria is needed
			for(int i = 0; i < fullArtifacts.size(); i++) {
				list.add(fullArtifacts.get(i));
		    }
			break;
			
		case "id":
			System.out.println("\nEnter the ID you want to search");
			search = userInput.nextLine();
			for(int i = 0; i < fullArtifacts.size(); i++) {
				if(fullArtifacts.get(i).getId() == Integer.parseInt(search)){
					list.add(fullArtifacts.get(i));
				}
		    }
			break;
			
		case "name":
			System.out.println("\nEnter the exact name you want to search");
			search = userInput.nextLine();
			for(int i = 0; i < fullArtifacts.size(); i++) {
				if(fullArtifacts.get(i).getName().equals(search)){
					list.add(fullArtifacts.get(i));
				}
		    }
			break;
			
		case "name contains":
			System.out.println("\nEnter part of the name you want to search");
			search = userInput.nextLine();
			for(int i = 0; i < fullArtifacts.size(); i++) {
				if(fullArtifacts.get(i).getName().contains(search)){
					list.add(fullArtifacts.get(i));
				}
		    }
			break;
			
		case "engagement":
			System.out.println("\nEnter the engagement type you want to search");
			search = userInput.nextLine();
			for(int i = 0; i < fullArtifacts.size(); i++) {
				if(fullArtifacts.get(i).getEngagementTime() == Integer.parseInt(search)){
					list.add(fullArtifacts.get(i));
				}
		    }
			break;
			
		case "type":
			System.out.println("Enter what type of Artifact it is\n1. PAINTING\n2. SCULPTURE\n3. POTTERY"
			+ "\n4. JEWELRY\n5. TEXTILE\n6. WEAPON\n7. TOOL\n8. FOSSIL\n9. COIN\n10. PHOTOGRAPH\n11. DOCUMENT"
			+ "\n12. OTHER\nPick one of these options");
			Boolean check = false;
			String input = userInput.nextLine();
			search = "";
			while(!check)
			{
				switch(input)
				{
					case "1": search = "PAINTING"; check = true; break;
					case "2": search = "SCULPTURE"; check = true; break;
					case "3": search = "POTTERY"; check = true; break;
					case "4": search = "JEWELRY"; check = true; break;
					case "5": search = "TEXTILE"; check = true; break;
					case "6": search = "WEAPON"; check = true; break;
					case "7": search = "TOOL"; check = true; break;
					case "8": search = "FOSSIL"; check = true; break;
					case "9": search = "COIN"; check = true; break;
					case "10": search = "PHOTOGRAPH"; check = true; break;
					case "11": search = "DOCUMENT"; check = true; break;
					case "12": search = "OTHER"; check = true; break;
					default: System.out.println("Invalid response, enter a number between 1-12"); input = userInput.nextLine(); break;
				}
			}
			
			for(int i = 0; i < fullArtifacts.size(); i++) {
				if(fullArtifacts.get(i).getType().equals(search)){
					list.add(fullArtifacts.get(i));
				}
		    }
			break;
		}
		
		Artifact temp;
		switch(sortBy) {
			
		case "name":
			for (int i = 0; i < list.size(); i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	            	if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) > 0) {
	                    temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j,  temp);
	                }
	            }
	        }
			break;
			
		case "type":
			for (int i = 0; i < list.size(); i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	            	if (list.get(i).getType().compareToIgnoreCase(list.get(j).getType()) > 0) {
	            		temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j,  temp);
	                }
	            }
	        }
			break;
			
		case "engagement": 
			for (int i = 0; i < list.size(); i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	            	if (list.get(i).getEngagementTime() > (list.get(j).getEngagementTime())) {
	            		temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j,  temp);
	                }
	            }
	        }
			break;
		}
		
		for(int i = 0; i < list.size(); i++) //No sort is needed for id as the list is automatically sorted by id
		{
			returnStrings.add(list.get(i).toString());
		}
		return returnStrings;
	}

    // --- Exhibit Management ---

	@Override
	public String createExhibit(String name, String description) throws Exception
	{
		fullExhibits.add(new Exhibit(exhibitIdNumber, name, description, new ArrayList<Artifact>()));
		exhibitIdNumber++;
		return "";
	}

	@Override
	public String getExhibitInfo(String exhibitId, String infoName) throws Exception
	{
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				switch(infoName) {
				case "name": return fullExhibits.get(i).getName();
				case "description": return fullExhibits.get(i).getDescription();
				case "artifacts": return fullExhibits.get(i).getArtifacts().toString(); 
				case "engagement": return Integer.toString(getExhibitEngagementTime(exhibitId));
				}
			}
		}
		return null;
	}

	@Override
	public String getExhibitArtifactSign(String exhibitId, String artifactId) throws Exception {
		for(int i = 0; i < fullSigns.size(); i++) {
			if(fullSigns.get(i).getExhibitId().equals(exhibitId) && fullSigns.get(i).getArtifactId().equals(artifactId)) {
				return fullSigns.get(i).getSign();
			}
		}
		return "Sign not found";
	}

	@Override
	public int getExhibitEngagementTime(String exhibitId) throws Exception {
		int time = 0;
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				for(int j = 0; j < fullExhibits.get(i).getArtifacts().size(); j++) {						
					time += fullExhibits.get(i).getArtifacts().get(j).getEngagementTime();
				}
			}
				
		}
		return time;
	}

	@Override
	public void updateExhibitInfo(String exhibitId, String infoName, String newValue) throws Exception
	{
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				switch(infoName){
					case "name": fullExhibits.get(i).setName(newValue); break;
					case "description":	fullExhibits.get(i).setDescription(newValue); break;	
				}
			}
		}
	}

	@Override
	public void deleteExhibit(String exhibitId) throws Exception
	{
		for(int i =0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				fullExhibits.remove(i);
			}
		}
	}

	@Override
	public ArrayList<String> findExhibits(String searchCriteria, String sortBy) throws Exception
	{
		ArrayList<String> returnStrings = new ArrayList<String>();
		ArrayList<Exhibit> list = new ArrayList<Exhibit>();	
		Scanner userInput = new Scanner(System.in);
		String input;
		
		switch(searchCriteria ) {
		default: //Returns all exhibits if no search criteria is needed
			for(int i = 0; i < fullExhibits.size(); i++) {
				list.add(fullExhibits.get(i));
		    }
			break;
			
		case "id":
			System.out.println("\nEnter the ID you want to search");
			input = userInput.nextLine();
			while(!isValidInput(input)) {
				input = userInput.nextLine();
			}
			for(int i = 0; i < fullExhibits.size(); i++) {
				if(fullExhibits.get(i).getId() == Integer.parseInt(input)){
					list.add(fullExhibits.get(i));
				}
		    }
			break;
			
		case "name":
			System.out.println("\nEnter the exact name you want to search");
			input = userInput.nextLine();
			while(!isValidInput(input)) {
				input = userInput.nextLine();
			}
			for(int i = 0; i < fullExhibits.size(); i++) {
				if(fullExhibits.get(i).getName().equals(input)){
					list.add(fullExhibits.get(i));
				}
		    }
			break;
			
		case "name contains":
			System.out.println("\nEnter part of the name you want to search");
			input = userInput.nextLine();
			while(!isValidInput(input)) {
				input = userInput.nextLine();
			}
			for(int i = 0; i < fullExhibits.size(); i++) {
				if(fullExhibits.get(i).getName().contains(input)){
					list.add(fullExhibits.get(i));
				}
		    }
		break;
		
		case "description":
			System.out.println("\nEnter part of the description you want to search");
			input = userInput.nextLine();
			while(!isValidInput(input)) {
				input = userInput.nextLine();
			}
			for(int i = 0; i < fullExhibits.size(); i++) {
				if(fullExhibits.get(i).getDescription().contains(input)){
					list.add(fullExhibits.get(i));
				}
		    }
		break;
		
		case "engagement": 
			System.out.println("\nEnter the engagement time you want to search");
			input = userInput.nextLine();
			while(!isValidInput(input)) {
				input = userInput.nextLine();
			}
			for(int i = 0; i < fullExhibits.size(); i++) {
				if(getExhibitEngagementTime(Integer.toString(fullExhibits.get(i).getId())) == Integer.parseInt(input)){
					list.add(fullExhibits.get(i));
				}
		    }
		break;
		
		}
		
		Exhibit temp;
		switch(sortBy) {
		case "name":
			for (int i = 0; i < list.size(); i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	            	if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) > 0) {
	                    temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j,  temp);
	                }
	            }
	        }
			break;
		case "description":
			for (int i = 0; i < list.size(); i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	            	if (list.get(i).getDescription().compareToIgnoreCase(list.get(j).getDescription()) > 0) {
	            		temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j,  temp);
	                }
	            }
	        }
			break;
			
		case "engagement": 
			for (int i = 0; i < list.size(); i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	            	if (getExhibitEngagementTime(Integer.toString(list.get(i).getId())) > getExhibitEngagementTime(Integer.toString(list.get(j).getId()))) {
	            		temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j,  temp);
	                }
	            }
	        }
			break;
		}
		
		String info = "Test";
		for(int i = 0; i < list.size(); i++) //No sort is needed for the id case as the list is sorted by Id by default
		{
			try {
				info = 	list.get(i).returnInfo(getExhibitEngagementTime(Integer.toString(list.get(i).getId())));
			} catch(Exception e){
				System.out.println(e.toString());
			}
			returnStrings.add("\n" + info);
		}
		return returnStrings;
	}

	@Override
	public void addArtifactToExhibit(String artifactId, String exhibitId, String sign) throws Exception
	{
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				for(int j = 0; j < fullArtifacts.size(); j++) {
					if(fullArtifacts.get(j).getId() == Integer.parseInt(artifactId))
					{
						ArrayList<Artifact> list = fullExhibits.get(i).getArtifacts();
						list.add(fullArtifacts.get(j));
						fullExhibits.get(i).setArtifacts(list);
						fullSigns.add(new ArtifactExhibitSign(artifactId, exhibitId, sign));
					}
				}
			}
		}
	}

	@Override
	public void removeArtifactFromExhibit(String artifactId, String exhibitId) throws Exception
	{
		Boolean check = false;
		for(int i = 0; i < fullSigns.size(); i++) {
			if(fullSigns.get(i).getArtifactId() == artifactId && fullSigns.get(i).getExhibitId() == exhibitId) {
				fullSigns.remove(i);
				for(int j = 0; j < fullExhibits.size(); j++){
					if(fullExhibits.get(j).getId() == Integer.parseInt(exhibitId)){
						for(int k = 0; k < fullArtifacts.size(); k++) {
							if(fullArtifacts.get(k).getId() == Integer.parseInt(artifactId)) {
								ArrayList<Artifact> list = fullExhibits.get(j).getArtifacts();
								list.remove(fullArtifacts.get(k));
								fullExhibits.get(j).setArtifacts(list);
								check = true;
							}
						}
					}
				}	
			}
		}
		
		if(check) {
			System.out.println("Artifact removed from exhibit");
		}
		else {
			System.out.println("This Exhibit does not contain this artifact");
		}
	}

	@Override
	public ArrayList<String> getExhibitArtifacts(String exhibitId) throws Exception
	{
		ArrayList<String> artifactInfo = new ArrayList<String>();
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				ArrayList<Artifact> artifacts = fullExhibits.get(i).getArtifacts();
				for(int j = 0; j < artifacts.size(); j++){
					artifactInfo.add(artifacts.get(j).toString());
				}
			}
		}
		
		return artifactInfo;
	}

	@Override
	public void reorderExhibitArtifacts(String exhibitId, ArrayList<String> artifactIds) throws Exception
	{
		int index = 0;
		ArrayList<Artifact> list = new ArrayList<Artifact>();
		for(int k = 0; k < fullExhibits.size(); k++) {
			if(fullExhibits.get(k).getId() == Integer.parseInt(exhibitId)) {
				index = k;
				for(int i = 0; i < fullExhibits.get(k).getArtifacts().size(); i++) {
					for(int j = 0; j < fullArtifacts.size(); j++) {
						if(fullArtifacts.get(j).getId() == Integer.parseInt(artifactIds.get(i))) {
							list.add(fullArtifacts.get(j));
						}
					}
				}
			}
		}
		fullExhibits.get(index).setArtifacts(list);

	}

    // --- Annual Plan Management ---

	@Override
	public String createAnnualPlan(int year) throws Exception
	{
		fullPlans.add(new AnnualPlan(year, planIdNumber));
		for(int i = 0; i < 13; i++) {
			fullMonthlyPlans.add(new MonthlyPlan(planIdNumber, i, "Hall A"));
			fullMonthlyPlans.add(new MonthlyPlan(planIdNumber, i, "Hall B"));
			fullMonthlyPlans.add(new MonthlyPlan(planIdNumber, i, "Hall C"));
		}

		planIdNumber++;
		return "";
	}

	@Override
	public String getAnnualPlan(int year) throws Exception
	{
		for(int i = 0; i < fullPlans.size(); i++) {
			if(fullPlans.get(i).getYear() == year) {
				return Integer.toString(fullPlans.get(i).getId());
			}
		}
		return null;
	}

	@Override
	public void addExhibitToAnnualPlan(String exhibitId, String hall, int month) throws Exception
	{
		Scanner userInput = new Scanner(System.in);
		System.out.println("What is the ID of the plan you want to add it to");
		String input = userInput.nextLine();
		while(isValidInput(input, "Plan")) {
			input = userInput.nextLine();
		}
		
		for(int i =0; i < fullMonthlyPlans.size(); i++) {
			if(fullMonthlyPlans.get(i).getMonth() == month && fullMonthlyPlans.get(i).getPlanId() == Integer.parseInt(input) && fullMonthlyPlans.get(i).getHall() == hall) {
				for(int j = 0; j < fullExhibits.size(); j++) {
					if(fullExhibits.get(j).getId() == Integer.parseInt(exhibitId)) {
						fullMonthlyPlans.get(i).setExhibit(fullExhibits.get(j));
					}
				}
			}
		}		
		
	}

	@Override
	public String getAnnualPlanExhibit(String planId, String hall, int month) throws Exception
	{
		for(int i = 0; i < fullMonthlyPlans.size(); i++) {
			if(fullMonthlyPlans.get(i).getMonth() == month && fullMonthlyPlans.get(i).getPlanId() == Integer.parseInt(planId) && fullMonthlyPlans.get(i).getHall().equals(hall)) {
				return fullMonthlyPlans.get(i).getExhibit().returnInfo(getExhibitEngagementTime(Integer.toString(fullMonthlyPlans.get(i).getExhibit().getId())));
			}
		}
		return "Annual Plan Exhibit not found";
	}

	@Override
	public void deleteAnnualPlan(String planId) throws Exception
	{
		for(int i = 0; i < fullPlans.size(); i++) {
			if(fullPlans.get(i).getId() == Integer.parseInt(planId)) {
				fullPlans.remove(i);
			}
		}
	}

	@Override
	public String getAnnualPlanInfo(String planId, String infoName) throws Exception
	{
		for(int i = 0; i < fullPlans.size(); i++) {
			if(fullPlans.get(i).getId() == Integer.parseInt(planId)) {
				switch(infoName) {
				case "year": return Integer.toString(fullPlans.get(i).getYear());
				case "total_exhibits":
					int count = 0;
					for(int j = 0; j < fullMonthlyPlans.size(); j++) {
						if(fullMonthlyPlans.get(i).getPlanId() == Integer.parseInt(planId)) {
							count++;
						}
					}
					return Integer.toString(count);
				}
			}
		}
		return null;
	}

	@Override
	public void updateAnnualPlanInfo(String planId, String infoName, String newValue) throws Exception
	{
		for(int i = 0; i < fullPlans.size(); i++) {
			if(fullPlans.get(i).getId() == Integer.parseInt(planId)) {
				switch(infoName) {
					case "year": fullPlans.get(i).setYear(Integer.parseInt(newValue));
				}
			}
		}
	}

    // --- Console UI Testing Support ---

	@Override
	public String getCurrentState() 
	{
		return "";
	}

	@Override
	public String getCurrentPrompt()
	{
		return currentState.toString();
	}

	@Override
    public boolean isValidInput(String input) 
    {
    	if(!input.isBlank()){
    		return true;
    	}
    	else {
    		System.out.println("Invalid input, please do not leave response blank");
    		return false;
    	}
    }
	
	 public boolean isValidInput(String input, String numCheckType) 
	 {
		int num = 0;
		
		try {
			num = Integer.parseInt(input);
		}
		catch(Exception e){
			System.out.println("Invalid input, please input an integer");
			return false;
		}
		
	 	switch(numCheckType) {
	 	case "Artifact":
	 		for(int i = 0; i < fullArtifacts.size(); i++) {
				if(num == fullArtifacts.get(i).getId()) {	
					return true;
				}
			}
		 	System.out.println("Invalid input, please enter an existing ID");
		 	return false;
		 	
	 	case "Exhibit": 
	 		for(int i = 0; i < fullExhibits.size(); i++) {
				if(num == fullExhibits.get(i).getId()) {		
					return true;
				}
			}
		 	System.out.println("Invalid input, please enter an existing ID");
		 	return false;
		 	
	 	case "Plan": 
	 		for(int i = 0; i < fullPlans.size(); i++) {
				if(num == fullPlans.get(i).getId()) {		
					return true;
				}
			}
		 	System.out.println("Invalid input, please enter an existing ID");
		 	return false;
		 	
	 	case "year": 
	 		if(Integer.toString(num).length() == 4) {
	 			if(num >= 2025) {
	 				return true;
	 			}
	 			else System.out.println("Please enter a current or future year");
	 		}
	 		else System.out.println("Please enter the year in the format yyyy");
	 		return false;
	 	
	 	default:
	 		if(num > 0){
	 			return true;
	 		}
	 		else {
	 			System.out.println("Invalid input, please do not enter an integer below 0");
	 			return false;
	 		}
	 	}
	 }
	 
	 public Boolean isValidInput(String input, ArrayList<Artifact> artifacts) {
		 try {
				int id = Integer.parseInt(input);
				for(int i = 0; i < artifacts.size(); i++) {
					if(artifacts.get(i).getId() == id) {
						return true;
					}
				}
				System.out.println("This artifact is not in this exhibit, please try again");
				return false;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				return false;
			}
	 }
		
	
    
	@Override
	public ArrayList<String> getValidInputs()
    {
    	return null;
    }

	@Override
    public ArrayList<String> getInvalidInputs()
    {
    	return null;
    }

	@Override
	public String processInput(String input)
    {
		switch(currentState) {
			case MAINMENU: return handleMainMenu(input);
			case ARTIFACTMENU: return handleArtifactMenu(input);
			case ARTIFACTAED: return handleArtifactAED(input);
			case ADDARTIFACT: return handleAddArtifact(input);
			case EDITARTIFACT: return handleEditArtifact(input);
			case DELETEARTIFACT: return handleDeleteArtifact(input);
			case SEARCHARTIFACT: return handleArtifactSearch(input);
			case SEARCHARTIFACTBYCRITERIA: return handleSearchArtifactByCriteria(input);
			case DISPLAYALLARTIFACTS: return handleDisplayAllArtifacts(input);
			case GETARTIFACTENGAGEMENTTIME: return handleGetEngagementTime(input);
			case EXHIBITMENU: return handleExhibitMenu(input);
			case EXHIBITAED: return handleExhibitAED(input);
			case ADDEXHIBIT: return handleAddExhibit(input);
			case EDITEXHIBIT: return handleEditExhibit(input);
			case DELETEEXHIBIT: return handleDeleteExhibit(input);
			case SEARCHEXHIBIT: return handleExhibitSearchMenu(input);
			case SEARCHEXHIBITBYCRITERIA: return handleSearchExhibitByCriteria(input);
			case DISPLAYALLEXHIBITS: return handleDisplayAllExhibits(input);
			case GETEXHIBITENGAGEMENTTIME: return handleGetExhibitEngagementTime(input);
			case GETEXHIBITARTIFACTS: return handleGetExhibitArtifacts(input);
			case MANAGEEXHIBITARTIFACTS: return handleManageExhibitArtifacts(input);
			case ADDEXHIBITARTIFACT: return handleAddExhibitArtifact(input);
			case DELETEEXHIBITARTIFACT: return handleRemoveExhibitArtifact(input);
			case REORDEREXHIBITARTIFACT: return handleReOrderExhibitArtifacts(input);
			case MANAGEEXHIBITSIGN: return handleManageExhibitSigns(input);
			case GETEXHIBITSIGN: return handleGetExhibitSign(input);
			case CHANGEEXHIBITSIGN: return handleChangeExhibitSign(input);
			case PLANMENU: return handlePlanMenu(input);
			case ADDPLAN: return handleAddPlan(input);
			case EDITPLAN: return handleEditPlan(input);
			case DELETEPLAN: return handleDeletePlan(input);
			case ADDPLANEXHIBIT: return handleAddPlanExhibit(input);
			case SEARCHFOREXHIBITINPLAN: return handleSearchForExhibitPlan(input);
			case GETANNUALPLAN: return handleGetAnnualPlan(input);
			default: return "Process failed";
		}
		
    }
	
	public void transitionTo(MenuState state) {
		currentState = state;
	}
	
	public String handleMainMenu(String input) {
		switch(input) {
			case "1": transitionTo(MenuState.ARTIFACTMENU); return "";
			case "2": transitionTo(MenuState.EXHIBITMENU); return "";
			case "3": transitionTo(MenuState.PLANMENU); return "";
			case "4": return "EXIT";
			default: return "Incorrect input, please retry";
		}
	}
	
	public String handleArtifactMenu(String input) {
		switch(input) {
			case "1": transitionTo(MenuState.ARTIFACTAED); return "";
			case "2": transitionTo(MenuState.SEARCHARTIFACT); return "";
			case "3": transitionTo(MenuState.MAINMENU); return "";
			default: return "Incorrect input, please retry";
		}
	}
	
	public String handleArtifactAED(String input) {
		switch(input) {
			case "1": transitionTo(MenuState.ADDARTIFACT); return "";
			case "2": transitionTo(MenuState.EDITARTIFACT); return "";
			case "3": transitionTo(MenuState.DELETEARTIFACT); return "";
			case "4": transitionTo(MenuState.ARTIFACTMENU); return "";
			default: return "Incorrect input, please retry";
		}
	}
	
	public String handleAddArtifact(String input) {
		Scanner userInput = new Scanner(System.in);
		String name = input;
		
		while(!isValidInput(name)) {
			name = userInput.nextLine();
		}
		
		System.out.println("What type of Artifact is it?\n1. PAINTING\n2. SCULPTURE\n3. POTTERY\n4. "
		+ "JEWELRY\n5. TEXTILE \n6. WEAPON\n7. TOOL\n8. FOSSIL\n9. COIN\n10. PHOTOGRAPH\n11. DOCUMENT\n"
		+ "12. OTHER\n Pick an option from 1-12");
		
		String typeInput = userInput.nextLine();
		String typeOfArtifact = "Initial";
		Boolean check = false;
		while(!check)
		{
			switch(typeInput)
			{
				case "1": typeOfArtifact = "PAINTING"; check = true; break;
				case "2": typeOfArtifact = "SCULPTURE"; check = true; break;
				case "3": typeOfArtifact = "POTTERY"; check = true; break;
				case "4": typeOfArtifact = "JEWELRY"; check = true; break;
				case "5": typeOfArtifact = "TEXTILE"; check = true; break;
				case "6": typeOfArtifact = "WEAPON"; check = true; break;
				case "7": typeOfArtifact = "TOOL"; check = true; break;
				case "8": typeOfArtifact = "FOSSIL"; check = true; break;
				case "9": typeOfArtifact = "COIN"; check = true; break;
				case "10": typeOfArtifact = "PHOTOGRAPH"; check = true; break;
				case "11": typeOfArtifact = "DOCUMENT"; check = true; break;
				case "12": typeOfArtifact = "OTHER"; check = true; break;
				default: System.out.println("Invalid response, enter a number between 1-12"); break;
			}
		}
		
		System.out.println("Enter the engagement time for the artifact");
		int engagement = 0;
		input = userInput.nextLine();
		while(!isValidInput(input, "")) {
			input = userInput.nextLine();
		}
		engagement = Integer.parseInt(input);
				
		System.out.println("Enter the description of the artifact");
		String desc = "";
		input = userInput.nextLine();
		while(!isValidInput(input)) {
			input = userInput.nextLine();
		}
		desc = input;
		
		try {
			return createArtifact(typeOfArtifact, name, desc, engagement);
		} catch (Exception e) {
			
		}
		return "";
	}
	
	public String handleEditArtifact(String input) {
		if(fullArtifacts.size() > 0) {	
			Scanner userInput = new Scanner(System.in);
			int id = 0;
			while(!isValidInput(input, "Artifact"))
			{
				input = userInput.nextLine();
			}
			id = Integer.parseInt(input);
		
			String variable = "";
			System.out.println("What value would you like to change\n1. Name\n2. Type\n3. Engagement Time\n4. Description\nSelect an option 1-4");
			input = userInput.nextLine();
			while(!isValidInput(input)){
				switch(input) {
					case "1": variable = "name"; break;
					case "2": variable = "type"; break;
					case "3": variable = "engagement"; break;
					case "4": variable = "description"; break;
					default: System.out.println("Incorrect input, please try again"); input = userInput.nextLine();
				}
			}
		
			System.out.println("What would you like to change it to");
			String value = "";
			while(!isValidInput(input, "") || !isValidInput(input)){
				input = userInput.nextLine();
			}
			value = input;
		
			try {
				updateArtifactInfo(Integer.toString(id), variable, value);
			} catch (Exception e) {}
		
			transitionTo(MenuState.ARTIFACTAED);
			
			return "";
			}
			else transitionTo(MenuState.ARTIFACTAED); return "No artifacts found";
	}
	
	public String handleDeleteArtifact(String input) {
		if(fullArtifacts.size() > 0) {	
			Scanner userInput = new Scanner(System.in);
			while(!isValidInput(input, "Artifact"))
			{
				input = userInput.nextLine();
			}
			Integer.parseInt(input);
		
			try {
				deleteArtifact(input);
			} catch (Exception e) {}
		
			transitionTo(MenuState.ARTIFACTAED);
			return "";
		}
		else transitionTo(MenuState.ARTIFACTAED); return "";
	}
	
	public String handleArtifactSearch(String input) {
		switch(input){
		case "1": transitionTo(MenuState.SEARCHARTIFACTBYCRITERIA); return "";
		case "2": transitionTo(MenuState.DISPLAYALLARTIFACTS); return "";
		case "3": transitionTo(MenuState.GETARTIFACTENGAGEMENTTIME); return "";
		case "4": transitionTo(MenuState.ARTIFACTMENU); return "";
		default: return "Incorrect input, please retry";
		}
	}
	
	public String handleSearchArtifactByCriteria(String input){
		Scanner userinput = new Scanner(System.in);
		String searchCriteria = "";
		Boolean check = false;
		while(!check){
			switch(input) {
				case "1": searchCriteria = "id"; check = true; break;
				case "2": searchCriteria = "name"; check = true; break;
				case "3": searchCriteria = "name contains"; check = true; break;
				case "4": searchCriteria = "type"; check = true; break;
				case "5": searchCriteria = "engagement"; check = true; break;
				default: System.out.println("Incorrect input, please try again"); input = userinput.nextLine(); break;
			}
		}
		
		System.out.println("What criteria would you like to sort by\n1. ID\n2. Name\n3. Type\n4. Engagement Time\nSelect an option 1-4");
		String sortCriteria = "";
		input = userinput.nextLine();
		check = false;
		while(!check){	
			switch(input) {
				case "1": sortCriteria = "id"; check = true; break;
				case "2": sortCriteria = "name"; check = true; break;
				case "3": sortCriteria = "type"; check = true; break;
				case "4": sortCriteria = "engagement"; check = true; break;
				default: System.out.println("Incorrect input, please try again"); input = userinput.nextLine(); break;
			}
		}
		
		ArrayList<String> searchedList = new ArrayList<String>();
		try {
			searchedList = findArtifacts(searchCriteria, sortCriteria);
		} catch (Exception e) {}
		
		for(int i = 0; i < searchedList.size(); i++) {
			System.out.println(searchedList.get(i));
		}
		transitionTo(MenuState.SEARCHARTIFACT); 
		
		return "";
	}
	
	
	public String handleDisplayAllArtifacts(String input){
		System.out.println("Current Artifacts:");
		String sortCriteria = "";
		Scanner userinput = new Scanner(System.in);
		while(sortCriteria.equals("")){	
			switch(input) {
				case "1": sortCriteria = "id"; break;
				case "2": sortCriteria = "name"; break;
				case "3": sortCriteria = "type"; break;
				case "4": sortCriteria = "engagement"; break;
				default: System.out.println("Incorrect input, please try again"); input = userinput.nextLine(); break;
			}
		}
		
		ArrayList<String> searchedList = new ArrayList<String>();
		try {
			searchedList = findArtifacts("", sortCriteria);
		} catch (Exception e) {}
		
		for(int i = 0; i < searchedList.size(); i++) {
			System.out.println(searchedList.get(i));
		}
		
		transitionTo(MenuState.SEARCHARTIFACT);
		
		return "";
	}
	
	public String handleGetEngagementTime(String input) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Artifact"))
		{
			try {
				Integer.parseInt(input);
			}
			catch(Exception e){
			} 
			
			input = userInput.nextLine();
		}
		
		try {
		    System.out.println(getArtifactEngagementTime(input));
		} catch (Exception e) {}
		transitionTo(MenuState.SEARCHARTIFACT);
		
		return "";
	}
	
	public String handleExhibitMenu(String input){
		switch(input) {
			case "1": transitionTo(MenuState.EXHIBITAED); break;
			case "2": transitionTo(MenuState.SEARCHEXHIBIT); break;
			case "3": transitionTo(MenuState.MANAGEEXHIBITARTIFACTS); break;
			case "4": transitionTo(MenuState.MAINMENU); break;
			default: System.out.println("Incorrect input, please retry");
		}
		return "";
	}
	
	public String handleExhibitAED(String input) {
		switch(input)
		{
			case "1": transitionTo(MenuState.ADDEXHIBIT); break;
			case "2": transitionTo(MenuState.EDITEXHIBIT); break;
			case "3": transitionTo(MenuState.DELETEEXHIBIT); break;
			case "4": transitionTo(MenuState.EXHIBITMENU); break;
			default: System.out.println("Incorrect input, please retry");
		}
		return "";
	}
	
	public String handleAddExhibit(String input) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input)) {
			input = userInput.nextLine();
		} 	
		String name = input;
		
		System.out.println("Add a description for the exhibit");
		input = userInput.nextLine();
		while(!isValidInput(input)) {
			input = userInput.nextLine();
		} 	
		String description = input;
		
		try {
			createExhibit(name, description);
		} catch (Exception e) {}
		
		transitionTo(MenuState.EXHIBITAED);
		
		return "";
	}
	
	public String handleEditExhibit(String input) {
		if(fullExhibits.size() > 0) {
		Scanner userInput = new Scanner(System.in);
		int id = 0;
		while(!isValidInput(input, "Exhibit"))
		{
			input = userInput.nextLine();
			try {
				id = Integer.parseInt(input);
			}
			catch(Exception e){
			} 
		}
		
		String variable = "";
		System.out.println("What value would you like to change\n1. Name\n2. Description\nSelect an option 1 or 2");
		input = userInput.nextLine();
		while(!isValidInput(input)){
			switch(input) {
				case "1": variable = "name"; break;
				case "2": variable = "description"; break;
				default: System.out.println("Incorrect input, please try again"); input = userInput.nextLine();
			}
		}
		
		System.out.println("What would you like to change it to");
		while(!isValidInput(input)) {
			input = userInput.nextLine();
		}
			
		try {
			updateExhibitInfo(Integer.toString(id), variable, input);
		} catch (Exception e) {}
		
		transitionTo(MenuState.EXHIBITAED);
		
		}
		else {
			System.out.println("No exhibits found");  
			transitionTo(MenuState.SEARCHEXHIBIT); 
		}
		return "";
	}
	
	public String handleDeleteExhibit(String input) {
		if(fullExhibits.size() > 0) {
		Scanner userInput = new Scanner(System.in);
		int id = 0;
		while(!isValidInput(input, "Exhibit"))
		{
			input = userInput.nextLine();
			try {
				id = Integer.parseInt(input);
			}
			catch(Exception e){
			} 
		}
		
		try {
			deleteExhibit(Integer.toString(id));
		} catch (Exception e) {}
		
		transitionTo(MenuState.EXHIBITAED);
		
		}
		else {
			System.out.println("No exhibits found");  
			transitionTo(MenuState.SEARCHEXHIBIT); 
		}
		return "";
	}
	
	public String handleExhibitSearchMenu(String input) {
		switch(input) {
			case "1": transitionTo(MenuState.SEARCHEXHIBITBYCRITERIA); return "";
			case "2": transitionTo(MenuState.DISPLAYALLEXHIBITS); return "";
			case "3": transitionTo(MenuState.GETEXHIBITENGAGEMENTTIME); return "";
			case "4": transitionTo(MenuState.GETEXHIBITARTIFACTS); return "";
			case "5": transitionTo(MenuState.EXHIBITMENU); return "";
			default: return "Incorrect input, please try again";
		}
	}
	
	public String handleSearchExhibitByCriteria(String input) {
		if(fullExhibits.size() > 0) {
			Scanner userinput = new Scanner(System.in);
			String searchCriteria = "";
			Boolean check = false;
			while(!check){
				switch(input) {
					case "1": searchCriteria = "id"; check = true; break;
					case "2": searchCriteria = "name"; check = true; break;
					case "3": searchCriteria = "name contains"; check = true; break;
					case "4": searchCriteria = "description"; check = true; break;
					default: System.out.println("Incorrect input, please try again"); input = userinput.nextLine(); break;
				}
			}
		
			System.out.println("What criteria would you like to sort by\n1. ID\n2. Name\n3. Type\n4. Engagement Time\nSelect an option 1-4");
			String sortCriteria = "";
			check = false;
			while(!check){	
				switch(userinput.nextLine()) {
					case "1": sortCriteria = "id"; check = true;break;
					case "2": sortCriteria = "name"; check = true; break;
					default: System.out.println("Incorrect input, please try again"); break;
				}
			}
		
			ArrayList<String> searchedList = new ArrayList<String>();
			try {
				searchedList = findExhibits(searchCriteria, sortCriteria);
			} catch (Exception e) {}
		
			for(int i = 0; i < searchedList.size(); i++) {
				System.out.println("\n" + searchedList.get(i));
			}
			transitionTo(MenuState.SEARCHEXHIBIT); 
			
		}
		else {
			System.out.println("No exhibits found");  
			transitionTo(MenuState.SEARCHEXHIBIT); 
		}
		return "";
	}
	
	public String handleDisplayAllExhibits(String input) {
		if(fullExhibits.size() > 0) {	
			String sortCriteria = "";
			Scanner userinput = new Scanner(System.in);
			while(sortCriteria.equals("")){	
				switch(input) {
					case "1": sortCriteria = "id"; break;
					case "2": sortCriteria = "name"; break;
					default: System.out.println("Incorrect input, please try again"); input = userinput.nextLine(); break;
				}
			}
		
			System.out.println("Current Exhibits:");

			ArrayList<String> searchedList = new ArrayList<String>();
			try {
				searchedList = findExhibits("", sortCriteria);
			} catch (Exception e) {}
		
			for(int i = 0; i < searchedList.size(); i++) {
				System.out.println(searchedList.get(i));
			}
		
			transitionTo(MenuState.SEARCHEXHIBIT); 
			
		}
		else {
			System.out.println("No exhibits found");
			transitionTo(MenuState.SEARCHEXHIBIT); 
		}
		return "";
	}
	
	public String handleGetExhibitEngagementTime(String input) {
		if(fullExhibits.size() > 0) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Exhibit"))
		{
			try {
				Integer.parseInt(input);
			}
			catch(Exception e){
			} 
			
			input = userInput.nextLine();
		}
		
		try {
		    System.out.println(getExhibitEngagementTime(input));
		} catch (Exception e) {}
		}
		else {
			System.out.println("No exhibits found");
		}
		transitionTo(MenuState.SEARCHEXHIBIT);
		return "";
	}
	
	public String handleGetExhibitArtifacts(String input) {
		if(fullExhibits.size() > 0) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Exhibit"))
		{
			try {
				Integer.parseInt(input);
			}
			catch(Exception e){
			} 
			input = userInput.nextLine();
		}
		
		ArrayList<String> artifacts = new ArrayList<String>();
		try {
			artifacts = getExhibitArtifacts(input);
		} catch (Exception e) {}
		
		for(int i = 0; i < artifacts.size(); i++) {
			System.out.println("\n" + artifacts.get(i));
		}
		}
		else {
			System.out.println("No exhibits found");
		}
		transitionTo(MenuState.SEARCHEXHIBIT);
		return "";
	}
	
	public String handleManageExhibitArtifacts(String input) {
		switch(input) {
			case "1": transitionTo(MenuState.ADDEXHIBITARTIFACT); break;
			case "2": transitionTo(MenuState.DELETEEXHIBITARTIFACT); break;
			case "3": transitionTo(MenuState.REORDEREXHIBITARTIFACT); break;
			case "4": transitionTo(MenuState.MANAGEEXHIBITSIGN); break;
			case "5": transitionTo(MenuState.EXHIBITMENU); break;
			default: System.out.println("Incorrect input, please try again");
		}
		return "";
	}
	
	public String handleAddExhibitArtifact(String input) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Exhibit"))
		{
			try {
				Integer.parseInt(input);
			}
			catch(Exception e){
			} 
			
			input = userInput.nextLine();
		}
		
		String exhibitId = input;
		
		System.out.println("Enter the id of the artifact you want to add");
		input = userInput.nextLine();
		while(!isValidInput(input, "Artifact"))
		{
			try {
				Integer.parseInt(input);
			}
			catch(Exception e){
			} 
			
			input = userInput.nextLine();
		}
		
		String artifactId = input;
		System.out.println("Enter what you want the sign to say about the artifact");
		
		input = userInput.nextLine();
		while(!isValidInput(input))
		{
			input = userInput.nextLine();
		}
		
		try {
			addArtifactToExhibit(artifactId, exhibitId, input);
		} catch (Exception e) {}
		
		transitionTo(MenuState.MANAGEEXHIBITARTIFACTS);
		
		return "";
	}
	
	public String handleRemoveExhibitArtifact(String input) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Exhibit"))
		{
			input = userInput.nextLine();
		}
		
		String exhibitId = input;
		
		System.out.println("Enter the id of the artifact you want to remove");
		input = userInput.nextLine();
		while(!isValidInput(input, "Artifact"))
		{
			input = userInput.nextLine();
		}
		
		String artifactId = input;
		
		try {
			removeArtifactFromExhibit(artifactId, exhibitId);
		} catch (Exception e) {}
		
		return "";
	}
	
	public String handleReOrderExhibitArtifacts(String input) {
		Scanner userInput = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<String>();
		
		while(!isValidInput(input, "Exhibit"))
		{
			input = userInput.nextLine();
		}
		
		String id = input;
		
		System.out.println("The current order of the artifacts is");
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(id)) {
				for(int j = 0; j < fullExhibits.get(i).getArtifacts().size(); j++) {
					System.out.println((j+1) + "" + fullExhibits.get(i).getArtifacts().get(j).toString()); 
				}
			}	
		}
		
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(id)) {
				for(int j = 0; j < fullExhibits.get(i).getArtifacts().size(); j++) {
					System.out.println("Enter the ID of the artifact Number " + (j+1) + " you want in the exhibit");
					input = userInput.nextLine();
					while(!isValidInput(input, fullExhibits.get(i).getArtifacts()))
					{
						input = userInput.nextLine();
					}
					list.add(input);
				}
			}
		}
		
		try {
			reorderExhibitArtifacts(id, list);
		} catch (Exception e) {}
		transitionTo(MenuState.MANAGEEXHIBITARTIFACTS);
		
		return "";
	}	
	
	public String handleManageExhibitSigns(String input) {
		switch(input) {
			case "1": transitionTo(MenuState.GETEXHIBITSIGN); break;
			case "2": transitionTo(MenuState.CHANGEEXHIBITSIGN); break;
			case "3": transitionTo(MenuState.MANAGEEXHIBITARTIFACTS); break;
			default: System.out.println("Incorrect input, please try again");
		}
		return "";
	}
	
	public String handleGetExhibitSign(String input) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Exhibit"))
		{
			input = userInput.nextLine();
		}
		String exhibitId = input;
		
		System.out.println("Enter the id of the artifact you want the sign for");
		input = userInput.nextLine();
		while(!isValidInput(input, "Artifact"))
		{
			input = userInput.nextLine();
		}
		
		try {
			System.out.println(getExhibitArtifactSign(exhibitId, input));
		} catch (Exception e) {}
		
		transitionTo(MenuState.MANAGEEXHIBITSIGN);
		
		return "";
	}
	
	public String handleChangeExhibitSign(String input) {
		Scanner userInput = new Scanner(System.in);
		while(!isValidInput(input, "Exhibit"))
		{
			input = userInput.nextLine();
		}
		String exhibitId = input;
		
		System.out.println("Enter the id of the artifact you want the sign for");
		input = userInput.nextLine();
		
		
		for(int i = 0; i < fullExhibits.size(); i++) {
			if(fullExhibits.get(i).getId() == Integer.parseInt(exhibitId)) {
				while(!isValidInput(input, fullExhibits.get(i).getArtifacts()))
				{
					input = userInput.nextLine();
				}
			}
		}
		String artifactId = input;
		
		System.out.println("Enter the new sign information");
		input = userInput.nextLine();
		while(!isValidInput(input))
		{
			input = userInput.nextLine();
		}
		String info = input;
		
		for(int i = 0; i < fullSigns.size(); i++) {
			if(fullSigns.get(i).getArtifactId() == artifactId && fullSigns.get(i).getExhibitId() == exhibitId) {
				fullSigns.get(i).setSign(info);			
			}
		}
		transitionTo(MenuState.MANAGEEXHIBITSIGN);
		
		return "";
	}
	
	public String handlePlanMenu(String input) {
		switch(input) {
		case "1": transitionTo(MenuState.ADDPLAN); break;
		case "2": transitionTo(MenuState.EDITPLAN); break;
		case "3": transitionTo(MenuState.DELETEPLAN); break;
		case "4": transitionTo(MenuState.ADDPLANEXHIBIT); break;
		case "5": transitionTo(MenuState.SEARCHFOREXHIBITINPLAN); break;
		case "6": transitionTo(MenuState.GETANNUALPLAN); break;
		case "7": transitionTo(MenuState.MAINMENU); break;
		default: System.out.println("Incorrect input, please try again");
		}
		return "";
	}
	
	public String handleAddPlan(String input) {
		Scanner userInput = new Scanner(System.in);
		while(isValidInput(input, "year")) {
			input = userInput.nextLine();
		}
		
		try {
			createAnnualPlan(Integer.parseInt(input));
		} catch (Exception e) {}
		
		return "";
	}
	
	public String handleEditPlan(String input) {
		Scanner userInput = new Scanner(System.in);
		while(isValidInput(input, "Plan")) {
			input = userInput.nextLine();
		}
		String id = input;
		
		System.out.println("What year do you want to change the plan to");
		while(isValidInput(input, "year")) {
			input = userInput.nextLine();
		}
		try {
			updateAnnualPlanInfo(id, "year", input);
		} catch (Exception e) {}
		
		return "";
	}
	
	public String handleDeletePlan(String input) {
		Scanner userInput = new Scanner(System.in);
		while(isValidInput(input, "Plan")) {
			input = userInput.nextLine();
		}
		
		try {
			deleteAnnualPlan(input);
		} catch (Exception e) {}
		
		return "";
	}
	
	public String handleAddPlanExhibit(String input) {
		Scanner userInput = new Scanner(System.in);
		while(isValidInput(input, "Exhibit")) {
			input = userInput.nextLine();
		}
		String exhibitId = input;
		
		System.out.println("What month would you like to add an exhibit to");
		Boolean check = false;
		int month = 0;
		while(!check)
		{
			switch(input.toLowerCase()) 
			{
				case "january": month = 1; check = true; break;
				case "february": month = 2; check = true; break;
				case "march": month = 3; check = true; break;
				case "april": month = 4; check = true; break;
				case "may": month = 5; check = true; break;
				case "june": month = 6; check = true; break;
				case "july": month = 7; check = true; break;
				case "august": month = 8; check = true; break;
				case "september": month = 9; check = true; break;
				case "october": month = 10; check = true; break;
				case "november": month = 11; check = true; break;
				case "december": month = 12; check = true; break;
				default: System.out.println("Invalid response, enter a valid month"); input = userInput.nextLine(); break;
			}
		}
		
		System.out.println("What exhibit hall will it be in\n1. Hall A\n2. Hall B\n3. Hall C\nSelect an option 1-3");
		check = false;
		String hall = null;
		while(!check)
		{
			switch(input.toLowerCase())
			{
				case "1": hall = "Hall A"; check = true; break;
				case "2": hall = "Hall B"; check = true; break;
				case "3": hall = "Hall C"; check = true; break;
				default: System.out.println("Invalid response, select an option 1-3"); input = userInput.nextLine(); break;
			}
		}
		
		try {
			addExhibitToAnnualPlan(exhibitId, hall, month);
		} catch (Exception e) {}
		
		return "";
	}
	
	public String handleSearchForExhibitPlan(String input) {
		Scanner userInput = new Scanner(System.in);
		while(isValidInput(input, "Plan")) {
			input = userInput.nextLine();
		}
		
		String planId = input;
		
		System.out.println("What month is the exhibit in");
		Boolean check = false;
		int month = 0;
		while(!check)
		{
			switch(input.toLowerCase()) 
			{
				case "january": month = 1; check = true; break;
				case "february": month = 2; check = true; break;
				case "march": month = 3; check = true; break;
				case "april": month = 4; check = true; break;
				case "may": month = 5; check = true; break;
				case "june": month = 6; check = true; break;
				case "july": month = 7; check = true; break;
				case "august": month = 8; check = true; break;
				case "september": month = 9; check = true; break;
				case "october": month = 10; check = true; break;
				case "november": month = 11; check = true; break;
				case "december": month = 12; check = true; break;
				default: System.out.println("Invalid response, enter a valid month"); input = userInput.nextLine(); break;
			}
		}
		
		System.out.println("What exhibit hall is it in\n1. Hall A\n2. Hall B\n3. Hall C\nSelect an option 1-3");
		check = false;
		String hall = null;
		while(!check)
		{
			switch(input.toLowerCase())
			{
				case "1": hall = "Hall A"; check = true; break;
				case "2": hall = "Hall B"; check = true; break;
				case "3": hall = "Hall C"; check = true; break;
				default: System.out.println("Invalid response, select an option 1-3"); input = userInput.nextLine(); break;
			}
		}
		
		try {
			getAnnualPlanExhibit(planId, hall, month);
		} catch (Exception e) {}
		
		return "";
	}
	
	public String handleGetAnnualPlan(String input) {
		Scanner userInput = new Scanner(System.in);
		while(isValidInput(input, "Plan")) {
			input = userInput.nextLine();
		}
		
		for(int i = 0; i < fullPlans.size(); i++) {
			if(fullPlans.get(i).getId() == Integer.parseInt(input)) {
				System.out.print(fullPlans.get(i).getYear());
			}
		}
		
		return "";
	}
}
