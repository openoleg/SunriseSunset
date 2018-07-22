package com.openoleg.sunrisesunset.domain.model;

public class Location {
    private float latitude, longitude;
    private String city, country;

    public Location(float latitude, float longitude, String city, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
