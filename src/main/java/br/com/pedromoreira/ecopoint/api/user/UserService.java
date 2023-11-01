package br.com.pedromoreira.ecopoint.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public List<UserDTO> getUsers() {
        return rep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){
        Optional<User> user = rep.findById(id);
        return user.map(UserDTO::create).orElse(null);
    }
}
