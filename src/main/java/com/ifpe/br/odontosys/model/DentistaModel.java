package com.ifpe.br.odontosys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
