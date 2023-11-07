package br.com.pedromoreira.ecopoint.api.item;

import br.com.pedromoreira.ecopoint.EcopointApplication;
import br.com.pedromoreira.ecopoint.api.infra.jwt.TokenService;
import br.com.pedromoreira.ecopoint.api.user.User;
import br.com.pedromoreira.ecopoint.api.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.http.HttpMethod.*;

@SpringBootTest(classes = EcopointApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseAPITest {
    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    protected UserRepository rep;

    @Autowired
    protected TokenService service;

    private String jwtToken = "";


    HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
        return headers;
    }

    @BeforeEach
    public void setupTest() {
        System.out.println("setupTest:");
        UserDetails user = rep.findByEmail("pedro@moreira.com");
        assertNotNull(user);

        jwtToken = service.generateToken((User) user);//JwtUtil.createToken(user);
        System.out.println("jwtToken gerado:");
        System.out.println(jwtToken);
        assertNotNull(jwtToken);
    }

    <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, POST, new HttpEntity<>(body, headers), responseType);
    }

    <T> ResponseEntity<T> put(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, PUT, new HttpEntity<>(body, headers), responseType);
    }

    <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, GET, new HttpEntity<>(headers), responseType);
    }

    <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, DELETE, new HttpEntity<>(headers), responseType);
    }
}
