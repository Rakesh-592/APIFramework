package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	//execute this code only when placeid is null
	//write a code that gives yo uplaceid
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		
		
	StepDefinition stepDef = new StepDefinition();
	
	if(StepDefinition.placeId ==null) {
	stepDef.add_place_payload_with("ram", "delhi", "india");
	stepDef.user_calls_with_http_request("addPlaceAPI", "POST");
	stepDef.verify_place_id_created_from_maps_with_using("ram", "getPlaceAPI");
	}
	}
	

}
