package com.ifpe.br.odontosys.DTO.request;

import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.model.EnderecoModel;
import com.ifpe.br.odontosys.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DentistaRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private String cro;
    private EnderecoModel endereco;
    private LocalDate dataNascimento;
    private String telefone;

    private UsuarioModel buildUsuario(){
        return new UsuarioModel(
                "ROLE_DENTISTA",
                email,
                senha);
    }

    public DentistaModel toEntity(){
        return DentistaModel.builder()
                .usuario(this.buildUsuario())
                .nome(nome)
                .cro(cro)
                .telefone(telefone)
                .endereco(endereco)
                .build();
    }
}
