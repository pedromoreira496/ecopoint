package br.com.pedromoreira.ecopoint.api.item;

import br.com.pedromoreira.ecopoint.EcopointApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcopointApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest extends BaseAPITest{


    private ResponseEntity<ItemDTO> getItem(String url){
        return get(url, ItemDTO.class);
    }

    private ResponseEntity<List<ItemDTO>> getItems(String url){
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<ItemDTO>>() {});
    }

    @Test
    void selectAll(){
        List<ItemDTO> items = getItems("/api/v1/items").getBody();
        assertNotNull(items);
        assertEquals(3, items.size());
    }

    @Test
    void selectById() {
        assertNotNull(getItem("/api/v1/items/1"));
        assertNotNull(getItem("/api/v1/items/2"));
        assertNotNull(getItem("/api/v1/items/3"));

        assertEquals(HttpStatus.NOT_FOUND, getItem("/api/v1/items/4").getStatusCode());
    }

    @Test
    void insert() {
        Item item = new Item();
        item.setTitulo("Organico");
        item.setImagem("organico.png");

        ResponseEntity response = post("/api/v1/items", item, null);
        System.out.println("response:");
        System.out.println(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);
        ItemDTO i = getItem(location).getBody();

        assertNotNull(i);
        assertEquals("Organico", i.getTitulo());

        delete(location, null);
        assertEquals(HttpStatus.NOT_FOUND, getItem(location).getStatusCode());
    }

    @Test
    void update() {
        Item item = new Item();
        item.setTitulo("Organico");
        item.setImagem("organico.png");

        ResponseEntity response = post("/api/v1/items", item, null);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);

        ItemDTO i = getItem(location).getBody();
        assertNotNull(i);
        assertEquals("Organico", i.getTitulo());
        assertEquals("organico.png", i.getImagem());

        Item i2 = new Item();
        i2.setTitulo("Eletronico");
        i2.setImagem("eletronico.png");

        response = put("/api/v1/items/" + i.getId(), i2, null);
        assertEquals("Eletronico", i2.getTitulo());
        assertEquals("eletronico.png", i2.getImagem());

        delete(location, null);
        assertEquals(HttpStatus.NOT_FOUND, getItem(location).getStatusCode());
    }

    @Test
    void delete() {
        Item item = new Item();
        item.setTitulo("Eletronico");
        item.setImagem("eletronico.png");

        ResponseEntity response = post("/api/v1/items", item, null);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);

        ItemDTO i = getItem(location).getBody();
        assertNotNull(i);
        assertEquals("Eletronico", i.getTitulo());
        assertEquals("eletronico.png", i.getImagem());

        response = delete("/api/v1/items/" + i.getId(), null);
        assertEquals(HttpStatus.NOT_FOUND, getItem(location).getStatusCode());
    }
}