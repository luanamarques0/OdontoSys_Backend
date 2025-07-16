package com.ifpe.br.odontosys.DTO.response;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifpe.br.odontosys.model.ConsultaModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsultasDentistaResponseDTO {

    private Long id;
    private String pacienteNome;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataConsulta;
    private String motivoConsulta;
    private String avaliacao;
    private String procedimentosRealizados;
    private String recomendacoes;

    public ConsultasDentistaResponseDTO(ConsultaModel consulta) {
        this.setId(consulta.getId());
        this.setPacienteNome(consulta.getPaciente().getNome());
        this.setDataConsulta(consulta.getDataConsulta());
        this.setMotivoConsulta(consulta.getMotivoConsulta());
        this.setAvaliacao(consulta.getAvaliacao());
        this.setProcedimentosRealizados(consulta.getProcedimentosRealizados());
        this.setRecomendacoes(consulta.getRecomendacoes());
    }

}
