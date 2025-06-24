package com.ifpe.br.odontosys.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_paciente")
public class PacienteModel extends BusinessModel {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private UsuarioModel usuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "paciente")
    private List<ConsultaModel> consultas = new ArrayList<>();

}
