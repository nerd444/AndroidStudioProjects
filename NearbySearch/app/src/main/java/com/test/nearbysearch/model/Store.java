package com.test.nearbysearch.model;

import java.io.Serializable;

public class Store implements Serializable {
    private String name;
    private String vicinity;
    private double lng;
    private double lat;

    public Store(String name, String vicinity, double lng, double lat) {
        this.name = name;
        this.vicinity = vicinity;
        this.lng = lng;
        this.lat = lat;
    }

    public Store(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
