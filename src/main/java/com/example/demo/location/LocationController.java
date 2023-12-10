package com.example.demo.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locationService.getLocations();
    }

    @GetMapping("/search/{searchString}")
    public List<Location> searchLocations(@PathVariable String searchString) {
        return locationService.searchLocations(searchString);
    }

    @GetMapping("/get/{id}")
    public Location getLocationById(@PathVariable String id) {
        Location location = locationService.getLocationById(id);
        return location;
    }

    @PostMapping("/add")
    public Location createLocation(@RequestBody Location newLocation) {
        Location createdLocation = locationService.createLocation(newLocation);
        return createdLocation;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable String id) {
        boolean success = locationService.deleteLocation(id);
        System.out.println(success);
    }

    @PutMapping("/edit/{id}")
    public Location updateLocation(@PathVariable String id, @RequestBody Location updatedData) {
        Location location = locationService.updateLocation(id, updatedData.getName(), updatedData.getAddress(), updatedData.getCity(), updatedData.getCountry(), updatedData.getLocationString());
        return location;
    }
}
