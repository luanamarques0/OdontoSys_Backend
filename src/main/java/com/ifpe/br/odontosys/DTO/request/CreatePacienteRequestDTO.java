package com.ifpe.br.odontosys.DTO.request;

import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.model.UsuarioModel;
import lombok.*;
import java.time.LocalDate;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePacienteRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String telefone;

    private UsuarioModel buildUsuario(){
        return new UsuarioModel(
                "ROLE_PACIENTE",
                email,
                senha);
    }

    public PacienteModel toEntity(){
        return PacienteModel.builder()
                .usuario(this.buildUsuario())
                .nome(nome)
                .dataNascimento(dataNascimento)
                .telefone(telefone)
                .build();
    }
}
