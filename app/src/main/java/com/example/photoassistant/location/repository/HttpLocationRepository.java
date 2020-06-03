package com.example.photoassistant.location.repository;

import com.example.photoassistant.location.Location;

import java.util.ArrayList;
import java.util.List;

public class HttpLocationRepository implements LocationRepository {

    @Override
    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<>();

        Location location = new Location();
        location.setId(1);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        locationList.add(location);

        new Location();
        location.setId(2);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        locationList.add(location);

        new Location();
        location.setId(3);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        locationList.add(location);

        new Location();
        location.setId(4);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        locationList.add(location);

        return locationList;
    }
}
