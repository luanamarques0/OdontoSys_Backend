package com.ifpe.br.odontosys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_dentista")

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DentistaModel extends BusinessModel{

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private UsuarioModel usuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cro;

    @Column(nullable = false)
    private String telefone;

    @OneToOne(targetEntity = EnderecoModel.class, cascade = CascadeType.PERSIST)
    private EnderecoModel endereco;

    @OneToMany(mappedBy = "dentista")
    private List<ConsultaModel> consultas;

}
