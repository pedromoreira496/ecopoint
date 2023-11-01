package br.com.pedromoreira.ecopoint.api.location_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Location_ItemService {

    @Autowired
    private Location_ItemRepository rep;

    public List<Location_ItemDTO> getLocation_Items() {
        return rep.findAll().stream().map(Location_ItemDTO::create).collect(Collectors.toList());
    }
}
