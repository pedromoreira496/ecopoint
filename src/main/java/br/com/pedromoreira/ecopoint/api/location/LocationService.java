package br.com.pedromoreira.ecopoint.api.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository rep;

    public List<LocationDTO> getLocations(){
        return rep.findAll().stream().map(LocationDTO::create).collect(Collectors.toList());
    }

    public LocationDTO getLocationById(Long id){
        Optional<Location> location = rep.findById(id);
        return location.map(LocationDTO::create).orElse(null);
    }

    public LocationDTO insert(Location location){
        Assert.isNull(location.getId(), "Não foi possível inserir o registro!");
        return LocationDTO.create(rep.save(location));
    }

    public LocationDTO update(Location l, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro!");

        Optional<Location> optional = rep.findById(id);

        if(optional.isPresent()){
            Location db = optional.get();

            if(l.getNome() != null){
                db.setNome(l.getNome());
            }
            if(l.getEmail() != null){
                db.setEmail(l.getEmail());
            }
            if(l.getCidade() != null){
                db.setCidade(l.getCidade());
            }
            if(l.getImagem() != null){
                db.setImagem(l.getImagem());
            }
            if(l.getWhatsapp() != null){
                db.setWhatsapp(l.getWhatsapp());
            }
            if(l.getUf() != null){
                db.setUf(l.getUf());
            }
            if(l.getLatitude() != 0){
                db.setLatitude(l.getLatitude());
            }
            if(l.getLongitude() != 0){
                db.setLongitude(l.getLongitude());
            }

            rep.save(db);

            return LocationDTO.create(db);
        } else {
            return null;
        }
    }
}
