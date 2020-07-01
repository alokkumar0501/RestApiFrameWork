package TestData;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class AddPlacePayload {
	AddPlace place=new AddPlace();
	
	public  AddPlace addPlacePayload(String name,String language,String address)
	{
		place.setAccuracy(50);;
		place.setAddress(address);
		place.setLanguage(language);
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(-33.427362);
		place.setLocation(loc);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		place.setTypes(mylist);
		place.setWebsite("http://rahulshettyacademy.com");
		return place;
		
	}
	
	public String  deletePlacePayload(String placeid)
	{
		return "{\r\n \"place_id\":\""+placeid+"\"\r\n}";
	}
	
	
}
