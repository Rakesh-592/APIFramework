Feature: validation place APIs

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>" "<address>" "<language>"
		When user calls "addPlaceAPI" with "POST" http request
		Then the API Call is success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		
		And verify place_Id created from maps with "<name>" using "getPlaceAPI"
		
Examples:
				|name 		 | address  |language|
				|RaviHouse | Banglore |Kannada |
#				|RamHome	 | Chennai  | Tamil  |
	
	
@DeletePlace	@Regression
Scenario: Verify deletePlaceAPI functionality is working 
		Given deletePlaceAPI Payload
		When user calls "deletePlaceAPI" with "POST" http request
		Then the API Call is success with status code 200
		And "status" in response body is "OK"