package com.ifpe.br.odontosys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "tb_consulta")

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaModel extends BusinessModel{

    @JsonIgnore
    @ManyToOne(targetEntity = PacienteModel.class)
    private PacienteModel paciente;

    @JsonIgnore
    @ManyToOne(targetEntity = DentistaModel.class)
    private DentistaModel dentista;

    @ManyToOne(targetEntity = EnderecoModel.class)
    private EnderecoModel endereco;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @Column
    private Double valorConsulta;

    @Column
    private String motivoConsulta;

    @Column
    private String avaliacao;

    @Column
    private String procedimentosRealizados;

    @Column
    private String recomendacoes;

    @Column
    private LocalDate voltaEsperada;

}
