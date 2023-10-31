package br.com.pedromoreira.ecopoint.api.user;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDTO {

    private Long id;
    private String nome;
    private String email;

    public static UserDTO create(User u){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(u, UserDTO.class);
    }
}
