package com.ifpe.br.odontosys.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedefinirSenhaRequestDTO {

    private String codigo;
    private String novaSenha;

}
