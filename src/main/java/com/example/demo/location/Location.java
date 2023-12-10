package com.example.demo.location;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {
    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String locationString;

    public Location() {

    }

    public Location(String id, String name, String address, String city, String country, String locationString) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.locationString = locationString;
    }

    public Location(String name, String address, String city, String country, String locationString) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.locationString = locationString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocationString() {
        return locationString;
    }

    public void setLocationString(String locationString) {
        this.locationString = locationString;
    }

    public void update(Location location) {
        this.name = location.getName();
        this.address = location.getAddress();
        this.city = location.getCity();
        this.country = location.getCountry();
        this.locationString = location.getLocationString();
    }
}
