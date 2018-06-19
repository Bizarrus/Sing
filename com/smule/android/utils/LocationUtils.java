package com.smule.android.utils;

import android.location.Location;
import android.location.LocationManager;
import com.smule.android.network.core.MagicNetwork;
import java.util.List;

public class LocationUtils {
    public static Location m19000a() {
        Location location;
        Location location2 = null;
        LocationManager locationManager = (LocationManager) MagicNetwork.d().getApplicationContext().getSystemService("location");
        if (locationManager != null) {
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders != null) {
                for (String lastKnownLocation : allProviders) {
                    Location lastKnownLocation2;
                    try {
                        lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                        if (lastKnownLocation2 == null || (location2 != null && location2.getTime() >= lastKnownLocation2.getTime())) {
                            lastKnownLocation2 = location2;
                        }
                    } catch (SecurityException e) {
                        lastKnownLocation2 = location2;
                    }
                    location2 = lastKnownLocation2;
                }
                location = location2;
                if (location == null) {
                    location = new Location("dummy");
                }
                if (location.getLatitude() == 0.0d && location.getLongitude() == 0.0d) {
                    location.setLatitude(37.78d);
                    location.setLongitude(-122.391d);
                }
                return location;
            }
        }
        location = null;
        if (location == null) {
            location = new Location("dummy");
        }
        location.setLatitude(37.78d);
        location.setLongitude(-122.391d);
        return location;
    }

    public static boolean m19001a(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2) || (((double) f) == 0.0d && ((double) f2) == 0.0d)) ? false : true;
    }
}
