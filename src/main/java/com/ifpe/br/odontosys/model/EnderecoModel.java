package com.ifpe.br.odontosys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_endereco")

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoModel extends BusinessModel{

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String cep;

    @Column
    private String complemento;
}
