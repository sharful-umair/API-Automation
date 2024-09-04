package org.sharfulumair.resources;

import org.sharfulumair.pojo.AddPlace;
import org.sharfulumair.pojo.DeletePlace;
import org.sharfulumair.pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace AddPlacePayload(String name, String language, String address,
                                    int accuracy, String number, String website, double lat, double lng )
    {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(accuracy);
        addPlace.setAddress(address);
        addPlace.setName(name);
        addPlace.setPhone_number(number);
        addPlace.setWebsite(website);
        addPlace.setLanguage(language);
        List<String> list = new ArrayList<>();
        list.add("shoe park");
        list.add("shop");
        addPlace.setTypes(list);
        Location location = new Location();
        location.setLat(lat);
        location.setLng(lng);
        addPlace.setLocation(location);
        return addPlace;
    }

    public DeletePlace DeletePlacePayload(String placeId)
    {
        DeletePlace deletePlace = new DeletePlace();
        deletePlace.setPlace_id(placeId);
        return deletePlace;
    }
}
