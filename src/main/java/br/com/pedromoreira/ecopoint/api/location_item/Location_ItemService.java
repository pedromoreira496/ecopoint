package br.com.pedromoreira.ecopoint.api.location_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Location_ItemService {

    @Autowired
    private Location_ItemRepository rep;

    public List<Location_ItemDTO> getLocation_Items() {
        return rep.findAll().stream().map(Location_ItemDTO::create).collect(Collectors.toList());
    }

    public Location_ItemDTO getLocation_ItemById(Long id){
        Optional<Location_Item> locationItem = rep.findById(id);
        return locationItem.map(Location_ItemDTO::create).orElse(null);
    }

    public Location_ItemDTO insert(Location_Item locationItem){
        Assert.isNull(locationItem.getId(), "Não foi possível inserir o registro!");
        return Location_ItemDTO.create(rep.save(locationItem));
    }
}
