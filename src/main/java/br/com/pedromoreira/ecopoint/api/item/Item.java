package br.com.pedromoreira.ecopoint.api.item;

import br.com.pedromoreira.ecopoint.api.location_item.Location_Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String titulo;
    private String imagem;

    @OneToMany(mappedBy = "item")
    private Set<Location_Item> locationItems;
}
