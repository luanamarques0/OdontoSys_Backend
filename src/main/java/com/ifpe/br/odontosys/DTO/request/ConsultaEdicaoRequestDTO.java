package com.ifpe.br.odontosys.DTO.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaEdicaoRequestDTO {
    
    @Size(max = 1000, message = "Avaliação deve ter no máximo 1000 caracteres")
    private String avaliacao;
    
    @Size(max = 1000, message = "Procedimentos realizados deve ter no máximo 1000 caracteres")
    private String procedimentosRealizados;
    
    @Size(max = 1000, message = "Recomendações deve ter no máximo 1000 caracteres")
    private String recomendacoes;
    
    @Size(max = 500, message = "Volta esperada deve ter no máximo 500 caracteres")
    private String voltaEsperada;
}
