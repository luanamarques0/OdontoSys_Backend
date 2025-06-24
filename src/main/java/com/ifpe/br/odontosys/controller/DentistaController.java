package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.CreateDentistaRequestDTO;
import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dentista")
public class DentistaController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private DentistaService dentistaService;

    @PostMapping
    public ResponseEntity<DentistaModel> createDentista(@RequestBody CreateDentistaRequestDTO dentistaRequest){
        DentistaModel dentista = dentistaRequest.toEntity();
        dentista.getUsuario().setSenha(
                encoder.encode(dentista.getUsuario().getPassword())
        );

        DentistaModel dentistaResponse = dentistaService.save(dentista);
        return new ResponseEntity<DentistaModel>(dentistaResponse, HttpStatus.CREATED);
    }

}
