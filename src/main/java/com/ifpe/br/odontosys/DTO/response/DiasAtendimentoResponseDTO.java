package com.ifpe.br.odontosys.DTO.response;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiasAtendimentoResponseDTO {

    private List<DataHorarios> disponibilidade;

    public DiasAtendimentoResponseDTO(List<DiasAtendimentoModel> diasAtendimentoList) {
        Map<String, List<String>> groupedByDate = diasAtendimentoList.stream()
            .filter(DiasAtendimentoModel::getDisponivel)
            .collect(Collectors.groupingBy(
                dia -> dia.getDataAtendimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Collectors.mapping(
                    dia -> dia.getDataAtendimento().format(DateTimeFormatter.ofPattern("HH:mm")),
                    Collectors.toList()
                )
            ));

        this.disponibilidade = groupedByDate.entrySet().stream()
            .map(entry -> new DataHorarios(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    @Setter
    @Getter
    public static class DataHorarios {
        private String data;
        private List<String> horarios;

        public DataHorarios(String data, List<String> horarios) {
            this.data = data;
            this.horarios = horarios;
        }
    }

}
