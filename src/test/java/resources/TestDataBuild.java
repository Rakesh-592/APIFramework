package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceGoogleApi;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlaceGoogleApi addPlacePayload(String name, String address, String language) {

		AddPlaceGoogleApi p = new AddPlaceGoogleApi();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		p.setLocation(loc);
		return p;
	
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\"place_id\":\""+placeId+"\"}";
	}

}
