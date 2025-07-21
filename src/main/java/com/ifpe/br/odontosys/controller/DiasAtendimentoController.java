package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.DiasAtendimentoRequestDTO;
import com.ifpe.br.odontosys.DTO.response.DiasAtendimentoResponseDTO;
import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import com.ifpe.br.odontosys.DTO.request.DiaAtualizarRequestDTO;
import com.ifpe.br.odontosys.service.DiasAtendimentoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diasAtendimento")
@CrossOrigin
public class DiasAtendimentoController {

    @Autowired
    private DiasAtendimentoService diasAtendimentoService;

    @PostMapping("{id}")
    public ResponseEntity<Void> save(@PathVariable Long id, @RequestBody DiasAtendimentoRequestDTO request){
        diasAtendimentoService.createDiasAtendimento(id, request.toListEntity());
        return ResponseEntity.ok().build();
    }

    @GetMapping("{cro}")
    public ResponseEntity<DiasAtendimentoResponseDTO> getDiasAtendimentoByDentistaId(@PathVariable String cro) {
        
        List<DiasAtendimentoModel> listaDisponibilidade = diasAtendimentoService.getDiasAtendimentoByDentistaCro(cro);
        DiasAtendimentoResponseDTO response = new DiasAtendimentoResponseDTO(listaDisponibilidade);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{dentistaId}/{id}")
    public ResponseEntity<?> updateDiaAtendimento(@PathVariable Long dentistaId, @PathVariable Long id, @RequestBody DiaAtualizarRequestDTO request) {
        return ResponseEntity.ok(diasAtendimentoService.updateDiaAtendimento(dentistaId, id, request.toEntity()));
    }

}
