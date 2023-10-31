package br.com.pedromoreira.ecopoint.api.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository rep;

    public List<ItemDTO> getItems() {
        return rep.findAll().stream().map(ItemDTO::create).collect(Collectors.toList());
    }

    public ItemDTO getItemById(Long id){
        Optional<Item> item = rep.findById(id);
        return item.map(ItemDTO::create).orElse(null);
    }
}