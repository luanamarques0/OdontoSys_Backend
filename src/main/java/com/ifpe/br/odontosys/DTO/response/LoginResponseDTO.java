package com.ifpe.br.odontosys.DTO.response;

public class LoginResponseDTO {

    private String token;
    private String tipoUsuario;

    public LoginResponseDTO(String token, String tipoUsuario) {
        this.token = token;
        this.tipoUsuario = tipoUsuario;
    }

    public String getToken() {
        return token;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}