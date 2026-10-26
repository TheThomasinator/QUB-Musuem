package part02;

import java.util.ArrayList;

import api.TestingQUBMuseumAPI;
import part01.AnnualPlan;
import part01.Artifact;
import part01.ArtifactExhibitSign;
import part01.Exhibit;
import part01.MonthlyPlan;
import part01.QUBMuseum;

public class TestingQUBMuseum implements TestingQUBMuseumAPI {
	
	//Tests can replace this instance with a new one if you want a clean start
    QUBMuseum api = new QUBMuseum();
    
    public static void main(String[] args) {
        
    	TestingQUBMuseum tester = new TestingQUBMuseum();
    	
        // UNIT TESTS
        
        // Run comprehensive test suite covering all API methods and edge cases, including error usage
    	tester.runArtifactCRUDTests();
    	tester.runExhibitCRUDTests();
    	tester.runAnnualPlanCRUDTests();

        //Add additional tests unit tests to cover any advanced functionality
        
        // END-TO-END TESTS

    	// Simulate user inputs to test plausible end to end tests that a user might perform
    	// Use to validate both correct and incorrect actions being handled correctly by the system
    	// Validate both the menu responses and the internal state of the system using the api
        tester.runMenuTests();
        
        // FUZZING TESTS
        // Randomly simulate user inputs with and without errors to validate that the user interface
        // is working correctly
        tester.runFuzzingTests();
    }
    
	@Override
    public void runArtifactCRUDTests() 
    {
		//Most validation is done in the isValidInput method which I have made several of, therefore for test cases for each method I will use that method to
		//check if values are valid, then put those values into the api method to confirm that isValidInput methods are correct
		
		//Because of the way I coded the system though, some validation is done through the handle methods and requires user input, I did not know how to make
		//automated tests for these cases as I couldn't figure out a way to input to the scanner without using additional libraries
		
		//Artifact creation tests
		String name;
		String type;
		String description;
		int engagementTime;
		
		System.out.println("Create Artifact: Valid inputs");
		System.out.println("Expected outcome: Artifact succesfully created");
		
		name = "Dino fossil";
		type = "FOSSIL"; //The type will not be tested as its validation is within a handle method and requires a user input
		description = "Its a fossil";
		engagementTime = 5; //Integers are inputed as strings to the method as the method checks if a user input is a valid int or not and a user input is always read in as a string
		
		if(api.isValidInput(name)) {
			if(api.isValidInput(description)) {
				if(api.isValidInput(Integer.toString(engagementTime), "")) { 
					try {
						api.createArtifact(type, name, description, engagementTime);
						System.out.println("Test passed");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Test failed");
					}
				}
				else {
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		
		System.out.println("\nCreate Artifact: Invalid inputs");
		System.out.println("Expected outcome: No artifact should be created");
		
		name = "";
		type = ""; //The type will not be tested as its validation is within a handle method and requires a user input
		description = "";
		engagementTime = 0; //Integers are inputed as strings to the method as the method checks if a user input is a valid int or not and a user input is always read in as a string
		
		if(!api.isValidInput(name)){
			if(!api.isValidInput(description)) {
				if(!api.isValidInput(Integer.toString(engagementTime), "")) { 
					System.out.println("Test passed");
				}
				else {
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		
		//Updating artifacts tests
		
		Artifact testArtifact;
		String testId;
		String newValue;
		String infoName;
		
		
		System.out.println("\nUpdate Artifact: Updating name");
		System.out.println("Expected outcome: Artifact name should be updated with correct info");
		
		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "A portrait");
		api.fullArtifacts.add(testArtifact);
		testId = "1";
		infoName = "name";
		newValue = "Portrait of King";
		
		if(api.isValidInput(testId, "Artifact")) {
			if(api.isValidInput(newValue)) {
				try {
					api.updateArtifactInfo(testId, infoName, newValue);
					if(api.fullArtifacts.get(0).getName().equals(newValue))
					{
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		
		api.fullArtifacts.clear();
		
		System.out.println("\nUpdate Artifact: Updating type");
		System.out.println("Expected outcome: Artifact type should be updated with correct info");
		
		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "A portrait");
		api.fullArtifacts.add(testArtifact);
		testId = "1";
		infoName = "type";
		newValue = "FOSSIL";
		
		if(api.isValidInput(testId, "Artifact")) {
			if(api.isValidInput(newValue)) {
				try {
					api.updateArtifactInfo(testId, infoName, newValue);
					if(api.fullArtifacts.get(0).getType().equals(newValue))
					{
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nUpdate Artifact: Updating description");
		System.out.println("Expected outcome: Artifact description should be updated with correct info");
		
		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "A portrait");
		api.fullArtifacts.add(testArtifact);
		testId = "1";
		infoName = "description";
		newValue = "A painting";
		
		if(api.isValidInput(testId, "Artifact")) {
			if(api.isValidInput(newValue)) {
				try {
					api.updateArtifactInfo(testId, infoName, newValue);
					if(api.fullArtifacts.get(0).getDescription().equals(newValue))
					{
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nUpdate Artifact: Updating engagement time");
		System.out.println("Expected outcome: Artifact engagement time should be updated with correct info");
		
		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "A portrait");
		api.fullArtifacts.add(testArtifact);
		testId = "1";
		infoName = "engagement";
		newValue = "20";
		
		if(api.isValidInput(testId, "Artifact")) {
			if(api.isValidInput(newValue, "")) {
				try {
					api.updateArtifactInfo(testId, infoName, newValue);
					if(api.fullArtifacts.get(0).getEngagementTime() == Integer.parseInt(newValue))
					{
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nUpdate Artifact: Updating a non existent artifact");
		System.out.println("Expected outcome: An error message should appear for the artifact not existing");
		
		testId = "1";
		infoName = "name";
		newValue = "Portrait of King";
		
		if(api.isValidInput(testId, "Artifact")) {
				try {
					api.updateArtifactInfo(testId, infoName, newValue);
					System.out.println("Test failed");

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Test failed");
				}
		}
		else {
			System.out.println("Test passed");
		}
		api.fullArtifacts.clear();
		
		//Deleting artifacts test
		
		System.out.println("\nDelete Artifact: Deleting an Artifact that exists");
		System.out.println("Expected outcome: The artifact should be deleted");
		
		testId = "1";
		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "A portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput(testId, "Artifact")) {
				try {
					api.deleteArtifact(testId);
					System.out.println("Test passed");

				} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nDelete Artifact: Deleting an Artifact that does not exist");
		System.out.println("Expected outcome: An error message  should appear for the artifact not existing");
		
		testId = "1";
		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "A portrait");
		
		if(api.isValidInput(testId, "Artifact")) {
				try {
					api.deleteArtifact(testId);
					System.out.println("Test failed");

				} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test passed");
		}
		api.fullArtifacts.clear();
		
		//Find artifact tests
		
		Artifact testArtifact2;
		Artifact testArtifact3;
		ArrayList<String> searchedList;
		
		System.out.println("\nFind Artifact: Sorting by id");
		System.out.println("Expected outcome: Artifacts returned and sorted by the ID");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "Portrait");
		testArtifact2 = new Artifact(2, "Dino Fossil", "FOSSIL", 20, "Dino fossil");
		testArtifact3 = new Artifact(3, "Statue", "SCULPTURE", 30, "Statue");
		
		api.fullArtifacts.add(testArtifact);
		api.fullArtifacts.add(testArtifact2);
		api.fullArtifacts.add(testArtifact3);

		try {
			searchedList = api.findArtifacts("", "id");
			if(searchedList.get(0).contains("ID: 1")){
				if(searchedList.get(0).contains("ID: 2")){
					if(searchedList.get(0).contains("ID: 3")){
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				}
				else {
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test failed");
		}
		
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact: Sorting by name");
		System.out.println("Expected outcome: Artifacts returned and sorted by the name");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "Portrait");
		testArtifact2 = new Artifact(2, "Dino Fossil", "FOSSIL", 20, "Dino fossil");
		testArtifact3 = new Artifact(3, "Statue", "SCULPTURE", 30, "Statue");
		
		api.fullArtifacts.add(testArtifact);
		api.fullArtifacts.add(testArtifact2);
		api.fullArtifacts.add(testArtifact3);

		try {
			searchedList = api.findArtifacts("", "name");
			if(searchedList.get(0).contains("Name: Dino Fossil")){
				if(searchedList.get(0).contains("Name: Portrait")){
					if(searchedList.get(0).contains("Name: Statue")){
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				}
				else {
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test failed");
		}
		
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact: Sorting by type");
		System.out.println("Expected outcome: Artifacts returned and sorted by the type");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 10, "Portrait");
		testArtifact2 = new Artifact(2, "Dino Fossil", "FOSSIL", 20, "Dino fossil");
		testArtifact3 = new Artifact(3, "Statue", "SCULPTURE", 30, "Statue");
		
		api.fullArtifacts.add(testArtifact);
		api.fullArtifacts.add(testArtifact2);
		api.fullArtifacts.add(testArtifact3);

		try {
			searchedList = api.findArtifacts("", "type");
			if(searchedList.get(0).contains("Type: FOSSIL")){
				if(searchedList.get(0).contains("Type: PAINTING")){
					if(searchedList.get(0).contains("Type: SCULPTURE")){
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				}
				else {
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test failed");
		}
		
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact: Sorting by engagement time");
		System.out.println("Expected outcome: Artifacts returned and sorted by the engagement time");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		testArtifact2 = new Artifact(2, "Dino Fossil", "FOSSIL", 15, "Dino fossil");
		testArtifact3 = new Artifact(3, "Statue", "SCULPTURE", 3, "Statue");
		
		api.fullArtifacts.add(testArtifact);
		api.fullArtifacts.add(testArtifact2);
		api.fullArtifacts.add(testArtifact3);

		try {
			searchedList = api.findArtifacts("", "type");
			if(searchedList.get(0).contains("Engagement Time: 3")){
				if(searchedList.get(0).contains("Engagement Time: 5")){
					if(searchedList.get(0).contains("Engagement Time: 15")){
						System.out.println("Test passed");
					}
					else {
						System.out.println("Test failed");
					}
				}
				else {
					System.out.println("Test failed");
				}
			}
			else {
				System.out.println("Test failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test failed");
		}
		
		api.fullArtifacts.clear();
		
		//Searching for artifacts with specific values cannot be automatically tested due to the method taking in a user input through scanners
		
		//Getting Artifact Info tests
		
		System.out.println("\nFind Artifact Info: Displaying the name of an existing artifact");
		System.out.println("Expected outcome: The returned value should be the name of the artifact");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput("1", "")) {
			try {
				if(api.getArtifactInfo("1", "name").equals(testArtifact.getName())){
					System.out.println("Test passed");
				}
				else {
					System.out.println("Test failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact Info: Displaying the type of an existing artifact");
		System.out.println("Expected outcome: The returned value should be the type of the artifact");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput("1", "")) {
			try {
				if(api.getArtifactInfo("1", "type").equals(testArtifact.getType())){
					System.out.println("Test passed");
				}
				else {
					System.out.println("Test failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact Info: Displaying the description of an existing artifact");
		System.out.println("Expected outcome: The returned value should be the description of the artifact");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput("1", "")) {
			try {
				if(api.getArtifactInfo("1", "description").equals(testArtifact.getDescription())){
					System.out.println("Test passed");
				}
				else {
					System.out.println("Test failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact Info: Displaying the engagement time of an existing artifact");
		System.out.println("Expected outcome: The returned value should be the engagement time of the artifact");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput("1", "")) {
			try {
				if(api.getArtifactInfo("1", "engagement").equals(Integer.toString(testArtifact.getEngagementTime()))){
					System.out.println("Test passed");
				}
				else {
					System.out.println("Test failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact Info: Displaying any info of a non existent artifact");
		System.out.println("Expected outcome: A new ArrayList<Artifact>() value should be returned");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		try {
			if(api.getArtifactInfo("2", "name") == null){
				System.out.println("Test passed");
			}
			else {
				System.out.println("Test failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test failed");
		}
		
		//Getting Artifact Engagement time tests
		System.out.println("\nFind Artifact Engagement Time: Displaying the engagement time of an existing artifact");
		System.out.println("Expected outcome: The correct engagement time should be returned");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput("1", "")) {
			try {
				if(api.getArtifactEngagementTime("1") == testArtifact.getEngagementTime()){
					System.out.println("Test passed");
				}
				else {
					System.out.println("Test failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test failed");
		}
		api.fullArtifacts.clear();
		
		System.out.println("\nFind Artifact Engagement Time: Displaying the engagement time of an non existing artifact");
		System.out.println("Expected outcome: An error message should show saying artifact does not exist");

		testArtifact = new Artifact(1, "Portrait", "PAINTING", 5, "Portrait");
		api.fullArtifacts.add(testArtifact);
		
		if(api.isValidInput("2", "Artifact")) {
			try {
				if(api.getArtifactEngagementTime("1") == testArtifact.getEngagementTime()){
					System.out.println("Test failed");
				}
				else {
					System.out.println("Test failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test failed");
			}
		}
		else {
			System.out.println("Test passed");
		}
		api.fullArtifacts.clear();
    }
	
	
    
	@Override
    public void runExhibitCRUDTests() 
    {
		    // Exhibit creation tests
		    String name;
		    String description;

		    System.out.println("\nCreate Exhibit: Valid inputs");
		    System.out.println("Expected outcome: Exhibit successfully created");

		    name = "Ancient Egypt";
		    description = "Artifacts from ancient Egypt";

		    if(api.isValidInput(name)) {
		        if(api.isValidInput(description)) {
		            try {
		                api.createExhibit(name, description);
		                System.out.println("Test passed");
		            } catch (Exception e) {
		                e.printStackTrace();
		                System.out.println("Test failed");
		            }
		        }
		        else {
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test failed");
		    }

		    System.out.println("\nCreate Exhibit: Invalid inputs");
		    System.out.println("Expected outcome: No exhibit should be created");

		    name = "";
		    description = "";

		    if(!api.isValidInput(name)){
		        if(!api.isValidInput(description)) {
		            System.out.println("Test passed");
		        }
		        else {
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test failed");
		    }

		    // Updating exhibit tests
		    Exhibit testExhibit;
		    String testId;
		    String newValue;
		    String infoName;

		    System.out.println("\nUpdate Exhibit: Updating name");
		    System.out.println("Expected outcome: Exhibit name should be updated");

		    testExhibit = new Exhibit(1, "Ancient Egypt", "Old description", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    testId = "1";
		    infoName = "name";
		    newValue = "Egyptian artifacts";

		    if(api.isValidInput(testId, "Exhibit")) {
		        if(api.isValidInput(newValue)) {
		            try {
		                api.updateExhibitInfo(testId, infoName, newValue);

		                if(api.fullExhibits.get(0).getName().equals(newValue)) {
		                    System.out.println("Test passed");
		                }
		                else {
		                    System.out.println("Test failed");
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		                System.out.println("Test failed");
		            }
		        }
		        else {
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test failed");
		    }

		    api.fullExhibits.clear();

		    System.out.println("\nUpdate Exhibit: Updating description");
		    System.out.println("Expected outcome: Exhibit description should be updated");

		    testExhibit = new Exhibit(1, "Ancient Egypt", "Old description", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    testId = "1";
		    infoName = "description";
		    newValue = "Updated museum description";

		    if(api.isValidInput(testId, "Exhibit")) {
		        if(api.isValidInput(newValue)) {
		            try {
		                api.updateExhibitInfo(testId, infoName, newValue);

		                if(api.fullExhibits.get(0).getDescription().equals(newValue)) {
		                    System.out.println("Test passed");
		                }
		                else {
		                    System.out.println("Test failed");
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		                System.out.println("Test failed");
		            }
		        }
		        else {
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test failed");
		    }

		    api.fullExhibits.clear();

		    System.out.println("\nUpdate Exhibit: Updating non-existent exhibit");
		    System.out.println("Expected outcome: Error should appear for invalid exhibit");

		    testId = "1";
		    infoName = "name";
		    newValue = "New Name";

		    if(api.isValidInput(testId, "Exhibit")) {
		        try {
		            api.updateExhibitInfo(testId, infoName, newValue);
		            System.out.println("Test failed");
		        } catch (Exception e) {
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test passed");  
		    }

		    api.fullExhibits.clear();

		    // Deleting exhibit tests
		    System.out.println("\nDelete Exhibit: Deleting an exhibit that exists");
		    System.out.println("Expected outcome: Exhibit should be deleted");

		    testId = "1";
		    testExhibit = new Exhibit(1, "Ancient Egypt", "Description", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);

		    if(api.isValidInput(testId, "Exhibit")) {
		        try {
		            api.deleteExhibit(testId);
		            System.out.println("Test passed");
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test failed");
		    }

		    api.fullExhibits.clear();

		    System.out.println("\nDelete Exhibit: Deleting an exhibit that does not exist");
		    System.out.println("Expected outcome: Error message should appear");

		    testId = "1";

		    if(api.isValidInput(testId, "Exhibit")) {
		        try {
		            api.deleteExhibit(testId);
		            System.out.println("Test failed");
		        } catch (Exception e) {
		            System.out.println("Test failed");
		        }
		    }
		    else {
		        System.out.println("Test passed");
		    }

		    api.fullExhibits.clear();

		    // Find exhibit tests
		    Exhibit testExhibit2;
		    Exhibit testExhibit3;
		    ArrayList<String> searchedList;

		    System.out.println("\nFind Exhibit: Sorting by id");
		    System.out.println("Expected: Exhibits returned and sorted by ID");

		    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
		    testExhibit2 = new Exhibit(2, "Rome", "B", new ArrayList<Artifact>());
		    testExhibit3 = new Exhibit(3, "Greece", "C", new ArrayList<Artifact>());

		    api.fullExhibits.add(testExhibit);
		    api.fullExhibits.add(testExhibit2);
		    api.fullExhibits.add(testExhibit3);

		    try {
		        searchedList = api.findExhibits("", "id");
		        if(searchedList.get(0).contains("ID: 1")) {
		            if(searchedList.get(0).contains("ID: 2")) {
		                if(searchedList.get(0).contains("ID: 3")) {
		                    System.out.println("Test passed");
		                }
		                else System.out.println("Test failed");
		            }
		            else System.out.println("Test failed");
		        }
		        else System.out.println("Test failed");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Test failed");
		    }

		    api.fullExhibits.clear();

		    System.out.println("\nFind Exhibit: Sorting by name");
		    System.out.println("Expected: Exhibits returned and sorted by name");

		    testExhibit = new Exhibit(1, "Rome", "A", new ArrayList<Artifact>());
		    testExhibit2 = new Exhibit(2, "Egypt", "B", new ArrayList<Artifact>());
		    testExhibit3 = new Exhibit(3, "Greece", "C", new ArrayList<Artifact>());

		    api.fullExhibits.add(testExhibit);
		    api.fullExhibits.add(testExhibit2);
		    api.fullExhibits.add(testExhibit3);

		    try {
		        searchedList = api.findExhibits("", "name");

		        if(searchedList.get(0).contains("Name: Rome")) {
		            if(searchedList.get(0).contains("Name: Egypt")) {
		                if(searchedList.get(0).contains("Name: Greece")) {
		                    System.out.println("Test passed");
		                }
		                else System.out.println("Test failed");
		            }
		            else System.out.println("Test failed");
		        }
		        else System.out.println("Test failed");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Test failed");
		    }

		    api.fullExhibits.clear();

		    // Get exhibit info tests
		    System.out.println("\nGet Exhibit Info: Getting name");
		    System.out.println("Expected: name returned");

		    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);

		    if(api.isValidInput("1", "")) {
		        try {
		            if(api.getExhibitInfo("1", "name").equals(testExhibit.getName())) {
		                System.out.println("Test passed");
		            }
		            else System.out.println("Test failed");
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Test failed");
		        }
		    }

		    api.fullExhibits.clear();

		    System.out.println("\nGet Exhibit Info: Getting description");
		    System.out.println("Expected: description returned");

		    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);

		    if(api.isValidInput("1", "")) {
		        try {
		            if(api.getExhibitInfo("1", "description").equals(testExhibit.getDescription())) {
		                System.out.println("Test passed");
		            }
		            else System.out.println("Test failed");
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Test failed");
		        }
		    }

		    api.fullExhibits.clear();

		    System.out.println("\nGet Exhibit Info: Non-existent exhibit");
		    System.out.println("Expected: new ArrayList<Artifact>() value");

		    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);

		    try {
		        if(api.getExhibitInfo("2", "name") == null) {
		            System.out.println("Test passed");
		        }
		        else System.out.println("Test failed");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Test failed");
		    }

		    api.fullExhibits.clear();
		    
		    //Add artifact to exhibit test
		    
		    Artifact testArtifact;
		    
		    System.out.println("\nAdd artifact to exhibit: Valid exhibit and valid artifact");
		    System.out.println("Expected: Artifact added");

		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.addArtifactToExhibit("1", "1", "Sign");
		    			if(api.fullSigns.get(0).getArtifactId() == "1" && api.fullSigns.get(0).getExhibitId() == "1") {
		    				System.out.println("Test passed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nAdd artifact to exhibit: Valid exhibit and non existent artifact");
		    System.out.println("Expected: An error message is shown");

		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.addArtifactToExhibit("1", "1", "Sign");
		    			if(api.fullSigns.get(0).getArtifactId() == "1" && api.fullSigns.get(0).getExhibitId() == "1") {
		    				System.out.println("Test failed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test passed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nAdd artifact to exhibit: Non existent exhibit and valid artifact");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.addArtifactToExhibit("1", "1", "Sign");
		    			if(api.fullSigns.get(0).getArtifactId() == "1" && api.fullSigns.get(0).getExhibitId() == "1") {
		    				System.out.println("Test failed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test passed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    //Remove artifact from exhibit tests
		    System.out.println("\nRemove artifact from exhibit: Valid exhibit and valid artifact");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    try {
		    	api.addArtifactToExhibit("1", "1", "there are fossils");
		    }
		    catch(Exception e){
		    	e.printStackTrace();
    			System.out.println("Test failed");
		    }
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.removeArtifactFromExhibit("1", "1");
		    			if(api.fullSigns.size() == 0) { 
		    				System.out.println("Test passed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nRemove artifact from exhibit: Valid exhibit and non existent artifact");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    try {
		    	api.addArtifactToExhibit("1", "1", "there are fossils");
		    }
		    catch(Exception e){
		    	e.printStackTrace();
    			System.out.println("Test failed");
		    }
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("2", "Artifact")) {
		    		try {
		    			api.removeArtifactFromExhibit("2", "1");
		    			if(api.fullSigns.size() == 0) { 
		    				System.out.println("Test failed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test passed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nRemove artifact from exhibit: Non existent exhibit and valid artifact");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    try {
		    	api.addArtifactToExhibit("1", "1", "there are fossils");
		    }
		    catch(Exception e){
		    	e.printStackTrace();
    			System.out.println("Test failed");
		    }
		    
		    if(api.isValidInput("2", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.removeArtifactFromExhibit("1", "2");
		    			if(api.fullSigns.size() == 0) { 
		    				System.out.println("Test failed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test passed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nRemove artifact from exhibit: Valid exhibit and artifact that is valid but not in exhibit");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.removeArtifactFromExhibit("1", "1");
		    			if(api.fullSigns.size() == 0) { 
		    				System.out.println("Test passed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nRemove artifact from exhibit: Valid exhibit and artifact that is valid but not in exhibit");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			api.removeArtifactFromExhibit("1", "1");
		    			if(api.fullSigns.size() == 0) { 
		    				System.out.println("Test passed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    //Get exhibit artifacts tests
		    
		    Artifact testArtifact2;
		    Artifact testArtifact3;
		    
		    System.out.println("\nGet artifacts from exhibit: Valid exhibit");
		    System.out.println("Expected: Artifacts are returned");

		    testArtifact = new Artifact(1, "Throne of an emperor", "OTHER", 10, "The throne of an ancient emperor");
		    testArtifact2 = new Artifact(2, "Painting of king", "PAINTING", 5, "A painting of a king");
		    testArtifact3 = new Artifact(3, "Statue of Zeus", "SCULPTURE", 20, "A statue of the god Zeus");
		    api.fullArtifacts.add(testArtifact);
		    api.fullArtifacts.add(testArtifact2);
		    api.fullArtifacts.add(testArtifact3);
		    
		    testExhibit = new Exhibit(1, "Powerful figures", "Powerful people in time", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    api.fullExhibits.get(0).setArtifacts(api.fullArtifacts);
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	try {
		   			if(api.fullExhibits.get(0).getArtifacts().equals(api.fullArtifacts)) { 
		   				System.out.println("Test passed");
		   			}
		   			else System.out.println("Test failed");
		   		}
		   		catch(Exception e) {
		   			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nGet artifacts from exhibit: Non existing exhibit");
		    System.out.println("Expected: Error message is shown");

		    testArtifact = new Artifact(1, "Throne of an emperor", "OTHER", 10, "The throne of an ancient emperor");
		    testArtifact2 = new Artifact(2, "Painting of king", "PAINTING", 5, "A painting of a king");
		    testArtifact3 = new Artifact(3, "Statue of Zeus", "SCULPTURE", 20, "A statue of the god Zeus");
		    api.fullArtifacts.add(testArtifact);
		    api.fullArtifacts.add(testArtifact2);
		    api.fullArtifacts.add(testArtifact3);
		    
		    testExhibit = new Exhibit(1, "Powerful figures", "Powerful people in time", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    api.fullExhibits.get(0).setArtifacts(api.fullArtifacts);
		    
		    if(api.isValidInput("2", "Exhibit")) {
		    	try {
		   			if(api.fullExhibits.get(0).getArtifacts().equals(api.fullArtifacts)) { 
		   				System.out.println("Test failed");
		   			}
		   			else System.out.println("Test failed");
		   		}
		   		catch(Exception e) {
		   			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test passed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    //Get exhibit artifact sign tests
		    
		    System.out.println("\nGet Exhibit Artifact sign: Valid exhibit and valid artifact");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    String testSign = "Test";
		    
		    testExhibit.setArtifacts(api.fullArtifacts);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", testSign));
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("1", "Artifact")) {
		    		try {
		    			if(api.getExhibitArtifactSign("1", "1").equals(testSign)) { 
		    				System.out.println("Test passed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nGet Exhibit Artifact sign: Valid exhibit and non existent artifact");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    testSign = "Test";
		    
		    testExhibit.setArtifacts(api.fullArtifacts);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", testSign));
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("2", "Artifact")) {
		    		try {
		    			if(api.getExhibitArtifactSign("1", "1").equals(testSign)) { 
		    				System.out.println("Test failed");
		    			}
				    	else System.out.println("Test failed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test passed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nGet Exhibit Artifact sign: Valid exhibit and valid artifact that isnt in the exhibit");
		    System.out.println("Expected: An error message is shown");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testArtifact2 = new Artifact(1, "Human fossil", "FOSSIL", 5, "A human fossil");
		    api.fullArtifacts.add(testArtifact2);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    
		    testSign = "Test";
		    
		    ArrayList<Artifact> list = new ArrayList<Artifact>();
		    list.add(testArtifact);
		    testExhibit.setArtifacts(list);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", testSign));
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	if(api.isValidInput("2", "Artifact")) {
		    		try {
		    			if(api.getExhibitArtifactSign("1", "2").equals(testSign)) { 
		    				System.out.println("Test failed");
		    			}
				    	else System.out.println("Test passed");
		    		}
		    		catch(Exception e) {
		    			e.printStackTrace();
		    			System.out.println("Test failed");
		    		}
		    	}
		    	else System.out.println("Test failed");
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    //Get exhibit engagement time tests
		    
		    System.out.println("\nGet Exhibit Engagement time: Valid exhibit");
		    System.out.println("Expected: Engagement time is returned");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testArtifact2 = new Artifact(1, "Human fossil", "FOSSIL", 5, "A human fossil");
		    api.fullArtifacts.add(testArtifact2);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    		    
		    testExhibit.setArtifacts(api.fullArtifacts);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", ""));
		    api.fullSigns.add(new ArtifactExhibitSign("2", "1", ""));
		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	try {
		    		if(api.getExhibitEngagementTime("1") == testArtifact.getEngagementTime() + testArtifact2.getEngagementTime()) { 
		   				System.out.println("Test passed");
		   			}
			    	else System.out.println("Test failed");
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nGet Exhibit Engagement time: Non existent exhibit");
		    System.out.println("Expected: Error message is returned");

		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testArtifact2 = new Artifact(1, "Human fossil", "FOSSIL", 5, "A human fossil");
		    api.fullArtifacts.add(testArtifact2);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    		    
		    testExhibit.setArtifacts(api.fullArtifacts);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", ""));
		    api.fullSigns.add(new ArtifactExhibitSign("2", "1", ""));
		    
		    if(api.isValidInput("2", "Exhibit")) {
		    	try {
		    		if(api.getExhibitEngagementTime("2") == testArtifact.getEngagementTime() + testArtifact2.getEngagementTime()) { 
		   				System.out.println("Test failed");
		   			}
			    	else System.out.println("Test failed");
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test passed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nGet Exhibit Engagement time: Valid exhibit with no artifacts");
		    System.out.println("Expected: Error message is returned");
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    		    		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	try {
		    		if(api.getExhibitEngagementTime("1") == testArtifact.getEngagementTime() + testArtifact2.getEngagementTime()) { 
		   				System.out.println("Test failed");
		   			}
			    	else System.out.println("Test passed");
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    //Re order exhibit artifacts tests
		    System.out.println("\nRe-Order exhibit artifacts: Valid exhibit");
		    System.out.println("Expected: Artifacts are correctly re-ordered");
		    
		    ArrayList<String> reOrderedList = new ArrayList<String>();
		    
		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testArtifact2 = new Artifact(2, "Human fossil", "FOSSIL", 5, "A human fossil");
		    api.fullArtifacts.add(testArtifact2);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    		    
		    testExhibit.setArtifacts(api.fullArtifacts);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", ""));
		    api.fullSigns.add(new ArtifactExhibitSign("2", "1", ""));
		    
		    reOrderedList.add("2");
		    reOrderedList.add("1");
		    		    		    
		    if(api.isValidInput("1", "Exhibit")) {
		    	try {
		    		api.reorderExhibitArtifacts("1", reOrderedList);
		    		if(api.getExhibitArtifacts("1").get(0).equals(testArtifact2.toString()) && api.getExhibitArtifacts("1").get(1).equals(testArtifact.toString())){ 
		   				System.out.println("Test passed");
		   			}
			    	else System.out.println("Test failed");
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test failed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
		    System.out.println("\nRe-Order exhibit artifacts: Non existent exhibit");
		    System.out.println("Expected: Artifacts are correctly re-ordered");
		    
		    reOrderedList = new ArrayList<String>();
		    
		    testArtifact = new Artifact(1, "Dino fossil", "FOSSIL", 10, "A fossil");
		    api.fullArtifacts.add(testArtifact);
		    
		    testArtifact2 = new Artifact(2, "Human fossil", "FOSSIL", 5, "A human fossil");
		    api.fullArtifacts.add(testArtifact2);
		    
		    testExhibit = new Exhibit(1, "Fossils", "fossils", new ArrayList<Artifact>());
		    api.fullExhibits.add(testExhibit);
		    		    
		    testExhibit.setArtifacts(api.fullArtifacts);
		    api.fullSigns.add(new ArtifactExhibitSign("1", "1", ""));
		    api.fullSigns.add(new ArtifactExhibitSign("2", "1", ""));
		    
		    reOrderedList.add("2");
		    reOrderedList.add("1");
		    		    		    
		    if(api.isValidInput("2", "Exhibit")) {
		    	try {
		    		api.reorderExhibitArtifacts("2", reOrderedList);
		    		if(api.getExhibitArtifacts("2").get(0).equals(testArtifact2.toString()) && api.getExhibitArtifacts("2").get(1).equals(testArtifact.toString())){ 
		   				System.out.println("Test failed");
		   			}
			    	else System.out.println("Test failed");
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
		    		System.out.println("Test failed");
		    	}
		    }
		    else System.out.println("Test passed");
		    api.fullArtifacts.clear();
		    api.fullExhibits.clear();
		    api.fullSigns.clear();
		    
    }
    
	@Override
    public void runAnnualPlanCRUDTests() 
    {
    	//Create annual plan tests
		
		System.out.println("\nCreate Annual plan: Valid year");
	    System.out.println("Expected: Annual plan is created");
	    		    		    
	    if(api.isValidInput("2025", "year")) {
	    	try {
	    		api.createAnnualPlan(2025);
	    		if(api.fullPlans.size() > 0) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test passed");
	    api.fullPlans.clear();
	    
	    System.out.println("\nCreate Annual plan: Invalid year");
	    System.out.println("Expected: Error message is shown");
	    		    		    
	    if(api.isValidInput("1999", "year")) {
	    	try {
	    		api.createAnnualPlan(1999);
	    		if(api.fullPlans.size() > 0) {
	    			System.out.println("Test failed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test passed");
	    api.fullPlans.clear();
	    
	    //Update annual plan tests
	    System.out.println("\nUpdate Annual plan: Valid id");
	    System.out.println("Expected: Year is updated");
	    		    		
	    api.fullPlans.add(new AnnualPlan(1, 2025));
	    
	    if(api.isValidInput("1", "Exhibit")) {
	    	try {
	    		api.updateAnnualPlanInfo("1", "year", "2026");
	    		if(api.fullPlans.get(0).getYear() == 2026) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    
	    System.out.println("\nUpdate Annual plan: Invalid id");
	    System.out.println("Expected: Error message is shown");
	    		    		
	    api.fullPlans.add(new AnnualPlan(1, 2025));
	    
	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		api.updateAnnualPlanInfo("1", "year", "2026");
	    		if(api.fullPlans.get(0).getYear() == 2026) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test passed");
	    api.fullPlans.clear();

	    
	    //Delete annual plan tests
	    System.out.println("\nDelete Annual plan: Valid plan");
	    System.out.println("Expected: Plan is deleted");
	    
	    api.fullPlans.add(new AnnualPlan(1, 2025));
	    		    		    
	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		api.deleteAnnualPlan("1");
	    		if(api.fullPlans.size() == 0) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    
	    System.out.println("\nDelete Annual plan: Non existing plan");
	    System.out.println("Expected: Error message is shwon");
	    	    		    		    
	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		api.deleteAnnualPlan("1");
	    		if(api.fullPlans.size() == 0) {
	    			System.out.println("Test failed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test passed");
	    api.fullPlans.clear();
	    
	    //Adding exhibits to plan tests cannot be done as they require a user input
	    
	    //Get Annual plan info tests
	    
	    System.out.println("\nGet Annual Plan info: Valid plan getting year");
	    System.out.println("Expected: The correct year is returned");
	    
	    api.fullPlans.add(new AnnualPlan(1, 2025));
	    		    		    
	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		if(api.getAnnualPlanInfo("1", "year").equals("2025")) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    api.fullExhibits.clear();
	    
	    System.out.println("\nGet Annual Plan info: Valid plan getting number of exhibits");
	    System.out.println("Expected: The correct year is returned");
	    
	    api.fullPlans.add(new AnnualPlan(1, 2025));
	    
	    Exhibit testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
	    Exhibit testExhibit2 = new Exhibit(2, "Rome", "B", new ArrayList<Artifact>());
	    Exhibit testExhibit3 = new Exhibit(3, "Greece", "C", new ArrayList<Artifact>());
	    api.fullExhibits.add(testExhibit);
	    api.fullExhibits.add(testExhibit2);
	    api.fullExhibits.add(testExhibit3);

	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 1, "Hall_A"));
	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 1, "Hall_B"));
	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 1, "Hall_C"));
	    
	    api.fullMonthlyPlans.get(0).setExhibit(testExhibit);
	    api.fullMonthlyPlans.get(1).setExhibit(testExhibit2);
	    api.fullMonthlyPlans.get(2).setExhibit(testExhibit3);

	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		if(api.getAnnualPlanInfo("1", "total_exhibits").equals("3")) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    api.fullExhibits.clear();
	    
	    //Get annual plan exhibit tests
	    System.out.println("\nGet Annual Plan Exhibit: Valid plan, valid hall and valid month");
	    System.out.println("Expected: The correct exhibit is returned");
	    
	    api.fullPlans.add(new AnnualPlan(1, 2025));
	    
	    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
	    api.fullExhibits.add(testExhibit);

	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 1, "Hall_A"));
	    
	    api.fullMonthlyPlans.get(0).setExhibit(testExhibit);

	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		if(api.getAnnualPlanExhibit("1", "Hall_A", 1).equals(testExhibit.returnInfo(api.getExhibitEngagementTime("1")))) {
	    			System.out.println("Test passed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    api.fullExhibits.clear();
	    
	    System.out.println("\nGet Annual Plan Exhibit: Non existent plan, valid hall and valid month");
	    System.out.println("Expected: The correct exhibit is returned");
	    	    
	    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
	    api.fullExhibits.add(testExhibit);

	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 1, "Hall_A"));
	    
	    api.fullMonthlyPlans.get(0).setExhibit(testExhibit);

	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		if(api.getAnnualPlanExhibit("1", "Hall_A", 1).equals(testExhibit.returnInfo(api.getExhibitEngagementTime("1")))) {
	    			System.out.println("Test failed");
	    		}
	    		else {
		   			System.out.println("Test failed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test passed");
	    api.fullPlans.clear();
	    api.fullExhibits.clear();
	    
	    System.out.println("\nGet Annual Plan Exhibit: Valid plan, non existing hall and valid month");
	    System.out.println("Expected: The correct exhibit is returned");
	    	    
	    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
	    api.fullExhibits.add(testExhibit);

	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 1, ""));
	    
	    api.fullMonthlyPlans.get(0).setExhibit(testExhibit);

	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		if(api.getAnnualPlanExhibit("1", "Hall_A", 1).equals(testExhibit.returnInfo(api.getExhibitEngagementTime("1")))) {
	    			System.out.println("Test failed");
	    		}
	    		else {
		   			System.out.println("Test passed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    api.fullExhibits.clear();
	    
	    System.out.println("\nGet Annual Plan Exhibit: Valid plan, valid hall and non existent month");
	    System.out.println("Expected: The correct exhibit is returned");
	    	    
	    testExhibit = new Exhibit(1, "Egypt", "A", new ArrayList<Artifact>());
	    api.fullExhibits.add(testExhibit);

	    api.fullMonthlyPlans.add(new MonthlyPlan(1, 3, "Hall_A"));
	    
	    api.fullMonthlyPlans.get(0).setExhibit(testExhibit);

	    if(api.isValidInput("1", "Plan")) {
	    	try {
	    		if(api.getAnnualPlanExhibit("1", "Hall_A", 1).equals(testExhibit.returnInfo(api.getExhibitEngagementTime("1")))) {
	    			System.out.println("Test failed");
	    		}
	    		else {
		   			System.out.println("Test passed");
	    		}
	    	}
    		catch(Exception e) {
    			e.printStackTrace();
	    		System.out.println("Test failed");
	    	}
	    }
	    else System.out.println("Test failed");
	    api.fullPlans.clear();
	    api.fullExhibits.clear();	        
    }
    
	@Override
    public void runMenuTests() 
    {
		String prompt;
	    String result;
	    
	    int numberOfErrors = 0;
	    int numberOfTests = 0;
	    
	    //Update with the actual prompt you expect to have for a the main menu
	    final String MAIN_MENU_PROMPT = "\n1. Artifact Menu\n2. Exhibit Menu\n3. Annual Plan Menu\n4. Exit \nSelect between options 1-4";
	    
	    try {
		    prompt = api.getCurrentPrompt();
		    result = api.processInput("1");
		    if (prompt != null && MAIN_MENU_PROMPT.equals(prompt)) {
		      System.out.println("PASS: Found student correctly");
		    } else {
		      System.out.println("FAIL: Failed to describe menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {
	    	
	    }

	    //Expand this example to cover a realistic user interaction
	    
	    //Add more end to end tests, starting from a fresh instance
	    api = new QUBMuseum(); 
    }

    
	@Override
    public void runFuzzingTests() 
    {
    	
    }
}