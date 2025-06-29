package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.PacienteRequestDTO;
import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.service.PacienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteModel> createPaciente(@RequestBody PacienteRequestDTO pacienteRequest) {
        PacienteModel paciente = pacienteRequest.toEntity();
        paciente.getUsuario().setSenha(
                encoder.encode(paciente.getUsuario().getPassword()));

        PacienteModel pacienteResponse = pacienteService.save(paciente);
        return new ResponseEntity<PacienteModel>(pacienteResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PacienteModel> listarTodos() {
        return pacienteService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PacienteRequestDTO pacienteRequest) {

        PacienteModel paciente = pacienteRequest.toEntity();
        pacienteService.update(id, paciente);
        return ResponseEntity.ok().build();
    }

}
