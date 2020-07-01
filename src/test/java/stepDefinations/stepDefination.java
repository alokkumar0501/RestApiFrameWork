package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payload.ApiResource;
import utility.utility;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import TestData.AddPlacePayload;

public class stepDefination extends utility{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	AddPlacePayload addPlace=new AddPlacePayload();
	static String place_id;
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(addPlace.addPlacePayload(name,language,address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		ApiResource resourceApi = ApiResource.valueOf(resource);
		
		if(httpMethod.equalsIgnoreCase("POST"))
		{
			response=res.when().post(resourceApi.getResource());
					
		}
		
		else if(httpMethod.equalsIgnoreCase("GET"))
		{
			response=res.when().get(resourceApi.getResource());
			
		}
		
	    
	}
	
	
	@Then("the Api call got Success with Status code {string}")
	public void the_Api_call_got_Success_with_Status_code(String statusCode) {
		assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
		System.out.println(response.asString());

	}


	@Then("{string} in Response body is {string}")
	public void in_Response_body_is(String keyValue, String expected) {
		
		assertEquals(getJsonPath(response, keyValue), expected);
		System.out.println(getJsonPath(response, keyValue));
		
	    
	}
    
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		 place_id = getJsonPath(response, "place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		 user_calls_with_http_request(resource,"GET");
		 String actualName=getJsonPath(response, "name");
		 assertEquals(actualName,expectedName);
	 
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		res=given().spec(requestSpecification()).body(addPlace.deletePlacePayload(place_id));
		
	}

}
