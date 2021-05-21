package com.example.restaurantmapapp.model;

import com.google.android.gms.maps.model.LatLng;

public class Locations {

    private int location_ID;
    private String location_name;

    private double lat;
    private double lon;

    public Locations(String location_name, double lat, double lon) {

        this.location_name = location_name;
        this.lat = lat;
        this.lon = lon;
    }

    public Locations() {
    }

    public int getLocation_ID() {
        return location_ID;
    }

    public void setLocation_ID(int location_ID) {
        this.location_ID = location_ID;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

