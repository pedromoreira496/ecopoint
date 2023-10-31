package br.com.pedromoreira.ecopoint.api.location;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class LocationDTO {

    private Long id;
    private String nome;
    private String imagem;
    private String email;
    private String whatsapp;
    private double latitude;
    private double longitude;
    private String cidade;
    private String uf;

    public static LocationDTO create(Location l){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(l, LocationDTO.class);
    }
}
