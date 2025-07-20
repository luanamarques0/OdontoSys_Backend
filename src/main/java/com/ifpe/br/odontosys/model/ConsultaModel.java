package com.ifpe.br.odontosys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifpe.br.odontosys.model.enums.StatusConsulta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "tb_consulta")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaModel extends BusinessModel{

    @JsonIgnore
    @ManyToOne(targetEntity = PacienteModel.class, fetch = FetchType.EAGER)
    private PacienteModel paciente;

    @JsonIgnore
    @ManyToOne(targetEntity = DentistaModel.class, fetch = FetchType.EAGER)
    private DentistaModel dentista;

    @ManyToOne(targetEntity = EnderecoModel.class, fetch = FetchType.EAGER)
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
    private String voltaEsperada;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private StatusConsulta statusConsulta;

}
