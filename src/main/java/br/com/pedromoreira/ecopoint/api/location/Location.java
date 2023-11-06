package br.com.pedromoreira.ecopoint.api.location;

import br.com.pedromoreira.ecopoint.api.location_item.Location_Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
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

    @NotBlank(message = "O nome não pode ser vazio!")
    @Size(min = 3, max = 50, message = "O nome precisa ter entre 3 a 50 caracteres!")
    private String nome;

    @NotBlank(message = "É necessário uma imagem!")
    @Size(min = 3, max = 50, message = "O nome da imagem precisa ter entre 3 a 50 caracteres!")
    private String imagem;

    @Email(message = "É necessário um email valido!")
    private String email;

    @NotBlank(message = "É necessário um número para contato")
    private String whatsapp;

    @NotNull(message = "É necessário fornecer uma localização!")
    private double latitude;
    @NotNull(message = "É necessário fornecer uma localização!")
    private double longitude;
    @NotBlank(message = "É necessário fornecer a cidade!")
    private String cidade;
    @NotBlank(message = "É necessário fornecer a UF!")
    private String uf;

    @OneToMany(mappedBy = "location")
    private Set<Location_Item> locationItems;
}
