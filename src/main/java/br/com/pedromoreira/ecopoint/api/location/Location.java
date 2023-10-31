package br.com.pedromoreira.ecopoint.api.location;

import br.com.pedromoreira.ecopoint.api.location_item.Location_Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String imagem;
    private String email;
    private String whatsapp;
    private double latitude;
    private double longitude;
    private String cidade;
    private String uf;

    @OneToMany(mappedBy = "location")
    private Set<Location_Item> locationItems;
}
