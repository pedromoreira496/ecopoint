package br.com.pedromoreira.ecopoint.api.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> selectAll(){
        return ResponseEntity.ok(service.getItems());
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> selectById(@PathVariable("id") Long id){
        ItemDTO item = service.getItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ItemDTO> insert(@RequestBody Item item){
        ItemDTO i = service.insert(item);
        URI location = getUri(i.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand().toUri();
    }
}
