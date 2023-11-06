package br.com.pedromoreira.ecopoint.api.item;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService service;

    @Test
    void getItems() {
        List<ItemDTO> items = service.getItems();
        assertEquals(3, items.size());
    }

    @Test
    void getItemById() {
        ItemDTO item = service.getItemById(1L);
        assertNotNull(item);
        assertEquals("Plastico", item.getTitulo());
    }

    @Test
    void insert() {
        Item item = new Item();
        item.setTitulo("Bateria");
        item.setImagem("bateria.png");

        ItemDTO i = service.insert(item);

        assertNotNull(i);

        Long id = i.getId();
        assertNotNull(id);

        i = service.getItemById(id);
        assertNotNull(i);

        assertEquals("Bateria", i.getTitulo());
        assertEquals("bateria.png", i.getImagem());

        service.delete(id);

        if(service.getItemById(id) != null){
            fail("O item foi excluido");
        }
    }

    @Test
    void update() {
        ItemDTO iDTO = service.getItemById(1L);
        String titulo = iDTO.getTitulo();
        iDTO.setTitulo("Eletronico");

        ModelMapper modelMapper = new ModelMapper();
        Item i = modelMapper.map(iDTO, Item.class);
        i.setImagem("eletronico.png");

        iDTO = service.update(i, i.getId());
        assertNotNull(iDTO);
        assertEquals("Eletronico", iDTO.getTitulo());

        i.setTitulo(titulo);
        iDTO = service.update(i, i.getId());
        assertNotNull(iDTO);
    }

    @Test
    void delete() {
        Item item = new Item();
        item.setTitulo("Organico");
        item.setImagem("organico.png");

        ItemDTO i = service.insert(item);
        assertNotNull(i);

        Long id = i.getId();
        assertNotNull(id);

        i = service.getItemById(id);
        assertNotNull(i);

        service.delete(id);
        if(service.getItemById(id) != null){
            fail("O item não foi excluido!");
        }
    }
}