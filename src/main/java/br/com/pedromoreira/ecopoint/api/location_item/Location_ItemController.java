package br.com.pedromoreira.ecopoint.api.location_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/locationitems")
public class Location_ItemController {

    @Autowired
    private Location_ItemService service;

    @GetMapping
    public ResponseEntity<List<Location_ItemDTO>> selectALl() {
        return ResponseEntity.ok(service.getLocation_Items());
    }

    @GetMapping("{id}")
    public ResponseEntity<Location_ItemDTO> selectById(@PathVariable("id") Long id){
        Location_ItemDTO locationItem = service.getLocation_ItemById(id);
        return locationItem != null ? ResponseEntity.ok(locationItem) : ResponseEntity.notFound().build();
    }
}
