package com.ifpe.br.odontosys.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ifpe.br.odontosys.DTO.request.ConsultaCadastroRequestDTO;
import com.ifpe.br.odontosys.DTO.request.ConsultaEdicaoRequestDTO;
import com.ifpe.br.odontosys.DTO.response.ConsultasDentistaResponseDTO;
import com.ifpe.br.odontosys.DTO.response.ConsultasPacienteResponseDTO;
import com.ifpe.br.odontosys.model.ConsultaModel;
import com.ifpe.br.odontosys.service.ConsultaService;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;


    @PostMapping
    public ResponseEntity<ConsultaModel> createConsulta(@RequestBody ConsultaCadastroRequestDTO consultaRequest) {
        ConsultaModel consultaModel = consultaService.saveConsulta(consultaRequest);

        return new ResponseEntity<>(consultaModel, HttpStatus.CREATED);
    }

    @GetMapping("/agenda/{dentistaId}")
    public ResponseEntity<List<ConsultasDentistaResponseDTO>> findConsultaByDentistaAndData( 
        @PathVariable Long dentistaId,
        @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
        ) {

        List<ConsultaModel> consultas = consultaService.findConsultaByDentistaAndData(dentistaId, data);
        
        var response = consultas.stream()
                .map(ConsultasDentistaResponseDTO::new)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ConsultasPacienteResponseDTO>> findConsultasDoUsuario(@PathVariable Long usuarioId) {
        List<ConsultaModel> consultas = consultaService.findConsultasDoUsuario(usuarioId);

        var response = consultas.stream()
                .map(ConsultasPacienteResponseDTO::new)
                .toList();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{consultaId}")
    public ResponseEntity<ConsultaModel> updateConsulta(
            @PathVariable Long consultaId,
            @RequestBody ConsultaEdicaoRequestDTO consultaEdicaoRequest) {
        
        ConsultaModel consultaAtualizada = consultaService.updateConsulta(consultaId, consultaEdicaoRequest);
        return new ResponseEntity<>(consultaAtualizada, HttpStatus.OK);
    }

    @PutMapping("cancelar/{consultaId}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long consultaId) {
        consultaService.cancelarConsulta(consultaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
