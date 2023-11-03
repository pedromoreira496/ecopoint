package br.com.pedromoreira.ecopoint.api.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<LocationDTO> insert(@RequestBody Location location){
        LocationDTO l = service.insert(location);
        URI loc = getUri(l.getId());
        return ResponseEntity.created(loc).build();
    }


    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

}
