package com.ifpe.br.odontosys.DTO.response;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifpe.br.odontosys.model.ConsultaModel;
import com.ifpe.br.odontosys.model.EnderecoModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsultasPacienteResponseDTO {

    private Long id;
    private String dentistaNome;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataConsulta;
    private String motivoConsulta;
    private String avaliacao;
    private String procedimentosRealizados;
    private String recomendacoes;
    private EnderecoModel endereco;
    private int statusConsulta;

    public ConsultasPacienteResponseDTO(ConsultaModel consulta) {
        this.setId(consulta.getId());
        this.setDentistaNome(consulta.getDentista().getNome());
        this.setDataConsulta(consulta.getDataConsulta());
        this.setMotivoConsulta(consulta.getMotivoConsulta());
        this.setAvaliacao(consulta.getAvaliacao());
        this.setProcedimentosRealizados(consulta.getProcedimentosRealizados());
        this.setRecomendacoes(consulta.getRecomendacoes());
        this.setEndereco(consulta.getEndereco());
        this.setStatusConsulta(consulta.getStatusConsulta().getCodigo());
    }

}
