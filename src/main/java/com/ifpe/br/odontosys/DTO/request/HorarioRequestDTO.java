package com.ifpe.br.odontosys.DTO.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HorarioRequestDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horario;

    @JsonCreator
    public HorarioRequestDTO(@JsonProperty("horario") LocalDateTime horario) {
        this.horario = horario;
    }

}
