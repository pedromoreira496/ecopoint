package br.com.pedromoreira.ecopoint.api.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    @Autowired
    private ItemService service;
}
