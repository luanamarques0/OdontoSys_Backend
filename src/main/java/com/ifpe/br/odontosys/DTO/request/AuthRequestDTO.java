package com.ifpe.br.odontosys.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    private String email;
    private String senha;

}
