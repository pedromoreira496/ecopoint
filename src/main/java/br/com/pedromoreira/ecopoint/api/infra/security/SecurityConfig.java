package br.com.pedromoreira.ecopoint.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http
        //        .authorizeRequests()//Quais rotas requerem autenticação
        //        .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll() //esse path é exceção, não requer autenticação
        //        .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()//e esse também
        //        .anyRequest().authenticated();
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.GET, "/swagger*/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                        .antMatchers(HttpMethod.GET, "/configuration/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/v1/items").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/api/v1/items/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/v1/items/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }
}
