package br.com.pedromoreira.ecopoint.api.location_item;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;

@Data
public class Location_ItemDTO {

    private Long id;
    private String descricao;
    private Timestamp associacao;

    public static Location_ItemDTO create(Location_Item li){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(li, Location_ItemDTO.class);
    }
}
