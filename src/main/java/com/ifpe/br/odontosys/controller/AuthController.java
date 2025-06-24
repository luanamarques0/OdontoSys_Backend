package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.AuthRequestDTO;
import com.ifpe.br.odontosys.model.UsuarioModel;
import com.ifpe.br.odontosys.security.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<HashMap<String, String>> login(@RequestBody AuthRequestDTO authRequestDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        String token = jwtService.generateToken((UsuarioModel) auth.getPrincipal());

        var response = new HashMap<String,String>();
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("testePaciente")
    public ResponseEntity<String> testePaciente(){
        return ResponseEntity.ok().body("FUNCIONOU PACIENTE");
    }

    @GetMapping("testeDentista")
    public ResponseEntity<String> testeDentista(){
        return ResponseEntity.ok().body("FUNCIONOU DENTISTA");
    }

    @GetMapping("testeAutenticado")
    public ResponseEntity<String> testeAutenticado(){
        return ResponseEntity.ok().body("FUNCIONOU AUTENTICADO");
    }

}
