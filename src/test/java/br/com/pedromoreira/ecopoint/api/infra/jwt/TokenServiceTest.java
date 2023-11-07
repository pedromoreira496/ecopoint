package br.com.pedromoreira.ecopoint.api.infra.jwt;

import br.com.pedromoreira.ecopoint.EcopointApplication;
import br.com.pedromoreira.ecopoint.api.user.User;
import br.com.pedromoreira.ecopoint.api.user.UserRepository;
import br.com.pedromoreira.ecopoint.api.user.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcopointApplication.class)
class TokenServiceTest {

    @Autowired
    protected UserRepository rep;

    @Autowired
    protected TokenService service;

    @Test
    void generateToken() {
        UserDetails user = rep.findByEmail("pedro@moreira.com");
        assertNotNull(user);

        String token = service.generateToken((User) user);
        assertNotNull(token);
    }

    @Test
    void validateToken() {
        UserDetails user = rep.findByEmail("pedro@moreira.com");
        assertNotNull(user);

        String token = service.generateToken((User) user);
        assertNotNull(token);

        String email = service.validateToken(token);
        assertNotNull(email);

        assertEquals("pedro@moreira.com", email);
        String role = String.valueOf(((User) user).getRole());
        assertEquals("ADMIN", role);
    }
}