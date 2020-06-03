package com.example.photoassistant.location;

import android.os.Bundle;

public class LocationBundleTranslator {

    private static final String ID = "LOCATION_ID";
    private static final String TITLE = "LOCATION_TITLE";
    private static final String SUBTITLE = "LOCATION_SUBTITLE";
    private static final String LATITUDE = "LOCATION_LATITUDE";
    private static final String LONGITUDE = "LOCATION_LONGITUDE";
    private static final String ENTRY_AMOUNT = "LOCATION_ENTRY_AMOUNT";

    public static Location toLocation(Bundle bundle) {
        Location location = new Location();
        location.setId(bundle.getLong(ID));
        location.setTitle(bundle.getString(TITLE));
        location.setSubtitle(bundle.getString(SUBTITLE));
        location.setLatitude(bundle.getDouble(LATITUDE));
        location.setLongitude(bundle.getDouble(LONGITUDE));
        location.setEntryAmount(bundle.getInt(ENTRY_AMOUNT));
        return location;
    }

    public static Bundle toBundle(Location location) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID, location.getId());
        bundle.putString(TITLE, location.getTitle());
        bundle.putString(SUBTITLE, location.getSubtitle());
        bundle.putDouble(LATITUDE, location.getLatitude());
        bundle.putDouble(LONGITUDE, location.getLongitude());
        bundle.putInt(ENTRY_AMOUNT, location.getEntryAmount());
        return bundle;
    }
}
