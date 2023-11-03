package br.com.pedromoreira.ecopoint.api.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/locations")
public class LocationController {

    @Autowired
    private LocationService service;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> selectAll(){
        return ResponseEntity.ok(service.getLocations());
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationDTO> selectById(@PathVariable("id") Long id){
        LocationDTO location = service.getLocationById(id);
        return location != null ? ResponseEntity.ok(location) : ResponseEntity.notFound().build();
    }

}
