package com.example.demo.location;

import com.example.demo.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class LocationService {
    private final LocationRepository repository;
    @Autowired
    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> getLocations() {
        return repository.findAll();
    }

    public Location getLocationById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Location createLocation(Location newLocation) {
        return repository.insert(newLocation);
    }

    public boolean deleteLocation(String id) {
        Location location = repository.findById(id).orElse(null);
        if (location != null) {
            repository.delete(location);
            return true;
        }
        return false;
    }

    public List<Location> searchLocations(String searchString) {
        List<Location> allLocations = repository.findAll();
        List<Location> searchResults = new ArrayList<>();
        for (Location location : allLocations) {
            if (location.getName().toLowerCase().contains(searchString.toLowerCase())) {
                searchResults.add(location);
            }
        }
        return searchResults;
    }

    public Location updateLocation(String id, String name, String address, String city, String country, String locationString){
        Location location = repository.findById(id).orElse(null);
        if (location != null) {
            location.setName(name);
            location.setAddress(address);
            location.setCity(city);
            location.setCountry(country);
            location.setLocationString(locationString);
            repository.save(location);
            return location;
        }
        return null;
    }

}
