package br.com.pedromoreira.ecopoint.api.item;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItemDTO {

    private Long id;
    private String titulo;
    private String imagem;

    public static ItemDTO create(Item i){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(i, ItemDTO.class);
    }
}
