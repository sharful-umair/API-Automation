package org.sharfulumair.stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace or @GetPlace or @UpdatePlace")
    public void beforeScenario() throws IOException {

        // Reset the placeId to ensure a new one is generated
        StepDefination.placeId = null;
        StepDefination stepDefination = new StepDefination();
        if(StepDefination.placeId == null)
        {
            stepDefination.add_place_payload("Frontline house Three", "Hindi", "31, side layout, cohen 09", 50, "(+91) 983 893 3937", "http://google.com", -38.383494, 33.427362);
            stepDefination.user_calls_with_http_request("AddPlaceAPI", "Post");
            stepDefination.verify_place_id_created_maps_to_using("Frontline house Three", "GetPlaceAPI");
        }
    }
}
