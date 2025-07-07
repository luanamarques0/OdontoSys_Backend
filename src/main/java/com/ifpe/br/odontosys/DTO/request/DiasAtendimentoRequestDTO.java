package com.ifpe.br.odontosys.DTO.request;

import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiasAtendimentoRequestDTO {

    private List<HorarioRequestDTO> horarios;

    public List<DiasAtendimentoModel> toListEntity(){
        return horarios.stream()
                .map(h -> toEntity(h.getHorario()))
                .toList();
    }

    private DiasAtendimentoModel toEntity(LocalDateTime data){
        return DiasAtendimentoModel
                .builder()
                .dataAtendimento(data)
                .disponivel(true)
                .build();
    }
}

