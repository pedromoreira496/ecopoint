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

    public UserDTO insert(User user){
        Assert.isNull(user.getId(), "Não foi possível inserir o registro!");
        return UserDTO.create(rep.save(user));
    }

    public UserDTO update(User u, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro!");

        Optional<User> optional = rep.findById(id);

        if(optional.isPresent()){
            User db = optional.get();

            if(u.getNome() != null){
                db.setNome(u.getNome());
            }
            if(u.getEmail() != null){
                db.setEmail(u.getEmail());
            }
            if(u.getPassword() != null){
                db.setPassword(u.getPassword());
            }

            rep.save(db);

            return UserDTO.create(db);
        } else {
            return null;
        }
    }
}
