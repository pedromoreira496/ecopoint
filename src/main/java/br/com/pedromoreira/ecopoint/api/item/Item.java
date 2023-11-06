package br.com.pedromoreira.ecopoint.api.item;

import br.com.pedromoreira.ecopoint.api.location_item.Location_Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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
    @NotBlank(message = "O titulo nao pode ser vazio")
    @Size(min = 5, max = 40, message = "O titulo precisa ter entre 10 a 40 caracteres")
    private String titulo;

    @NotBlank(message = "É necessário haver o envio de uma imagem")
    private String imagem;

    @OneToMany(mappedBy = "item")
    private Set<Location_Item> locationItems;
}
