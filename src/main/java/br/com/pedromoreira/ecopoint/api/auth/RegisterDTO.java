package br.com.pedromoreira.ecopoint.api.auth;

import br.com.pedromoreira.ecopoint.api.user.UserRole;

public record RegisterDTO(String nome, String email, String password, UserRole role) {
}
