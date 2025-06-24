package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.CreatePacienteRequestDTO;
import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteModel> createPaciente(@RequestBody CreatePacienteRequestDTO pacienteRequest){
        PacienteModel paciente = pacienteRequest.toEntity();
        paciente.getUsuario().setSenha(
                encoder.encode(paciente.getUsuario().getPassword())
        );

        PacienteModel pacienteResponse = pacienteService.save(paciente);
        return new ResponseEntity<PacienteModel>(pacienteResponse, HttpStatus.CREATED);
    }

}
