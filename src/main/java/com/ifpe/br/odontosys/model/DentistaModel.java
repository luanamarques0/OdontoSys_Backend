package com.ifpe.br.odontosys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cro;

    @Column(nullable = false)
    private String telefone;

    @OneToOne(targetEntity = EnderecoModel.class)
    private EnderecoModel endereco;

    @OneToMany(mappedBy = "dentista")
    private List<ConsultaModel> consultas;

}
