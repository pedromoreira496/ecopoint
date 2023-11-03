package br.com.pedromoreira.ecopoint.api.auth;

import br.com.pedromoreira.ecopoint.api.infra.jwt.TokenService;
import br.com.pedromoreira.ecopoint.api.user.User;
import br.com.pedromoreira.ecopoint.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository rep;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());


        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        if(rep.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.nome(), data.email(), encryptPassword, data.role());
        rep.save(newUser);
        return ResponseEntity.ok().build();
    }
}
