package com.ifpe.br.odontosys.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifpe.br.odontosys.model.DiasAtendimentoModel;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DiaAtualizarRequestDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data;

    private boolean disponivel;

    public DiasAtendimentoModel toEntity() {
        return DiasAtendimentoModel.builder()
                .dataAtendimento(data)
                .disponivel(disponivel)
                .build();
    }

}
