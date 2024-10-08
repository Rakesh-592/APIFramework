package stepdefinitions;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceGoogleApi;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.restassured.path.json.JsonPath;

public class StepDefinition extends Utils {
	
	public static RequestSpecification res; //class level declaration
	ResponseSpecification responsespec;
	Response response;
	
	public static String placeId; 
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
	    
//		responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		res = given().spec(requestSpecification())
		.body(data.addPlacePayload(name,address,language));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
//		constructor will be called with value Of resource which you will pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
	    
		responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) {
		response = res.when().post(resourceAPI.getResource());
//				then().spec(responsespec).extract().response();
		} else if(method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		}
	}
	@Then("the API Call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
//	    System.out.println(response.getStatusCode());
//		assertNotNull("Response is null", response);
//		assertEquals(response.getStatusCode(),int1.intValue());
		assertEquals(response.getStatusCode(),200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    
//		String res = response.asString();
//		js = new JsonPath(res);
//		assertEquals(js.get(keyValue),expectedValue);
		assertEquals(jsonPath(response, keyValue),expectedValue);
		
	}
	
	
	@Then("verify place_Id created from maps with {string} using {string}")
	public void verify_place_id_created_from_maps_with_using(String expectedName, String resource) throws IOException {
		placeId = jsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource,"GET");
		String actualName = jsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	}
	
	@Given("deletePlaceAPI Payload")
	public void delete_place_api_payload() throws IOException {
	    
		res = given().spec(requestSpecification())
				.body(data.deletePlacePayload(placeId));
		
	}

}
