package br.com.pedromoreira.ecopoint.api.location_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Location_ItemDTO> insert(@RequestBody Location_Item locationItem){
        Location_ItemDTO li = service.insert(locationItem);
        URI locItem = getUri(li.getId());
        return ResponseEntity.created(locItem).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
