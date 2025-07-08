package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.DiasAtendimentoRequestDTO;
import com.ifpe.br.odontosys.service.DiasAtendimentoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diasAtendimento")
public class DiasAtendimentoController {

    @Autowired
    private DiasAtendimentoService diasAtendimentoService;

    @PostMapping("{id}")
    public ResponseEntity<Void> save(@PathVariable Long id, @RequestBody DiasAtendimentoRequestDTO request) {
        diasAtendimentoService.createDiasAtendimento(id, request.toListEntity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid DiasAtendimentoRequestDTO request) {
        diasAtendimentoService.updateDiasAtendimento(id, request.toListEntity());
        return ResponseEntity.ok().build();
    }

}
