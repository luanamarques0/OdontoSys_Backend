package com.ifpe.br.odontosys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiasAtendimentoModel extends BusinessModel{

    @JsonIgnore
    @ManyToOne(targetEntity = DentistaModel.class)
    @JoinColumn(name = "dentista_id")
    private DentistaModel dentista;

    @Column
    private LocalDateTime dataAtendimento;

    @Column(nullable = false)
    private Boolean disponivel;

}
