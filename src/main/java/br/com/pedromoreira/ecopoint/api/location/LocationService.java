package br.com.pedromoreira.ecopoint.api.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository rep;

    public List<LocationDTO> getLocations(){
        return rep.findAll().stream().map(LocationDTO::create).collect(Collectors.toList());
    }
}
