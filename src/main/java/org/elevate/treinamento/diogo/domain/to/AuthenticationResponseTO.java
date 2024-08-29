package org.elevate.treinamento.diogo.domain.to;

public class AuthenticationResponseTO {
    private final String jwt;

    public AuthenticationResponseTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}