package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeSscenario() throws IOException
	{
		
		stepDefination step=new stepDefination();
		if(stepDefination.place_id==null)
		{
		step.add_place_payload_with("Alok", "English", "bangalore");
		step.user_calls_with_http_request("AddPlaceApi", "POST");
		step.verify_place_id_created_maps_to_using("Alok", "GetPlaceApi");
		}
	}

}
