package com.ifpe.br.odontosys.controller;

import com.ifpe.br.odontosys.DTO.request.EmailRedefinicaoDeSenhaRequestDTO;
import com.ifpe.br.odontosys.DTO.request.RedefinirSenhaRequestDTO;
import com.ifpe.br.odontosys.model.ResetPasswordModel;
import com.ifpe.br.odontosys.model.UsuarioModel;
import com.ifpe.br.odontosys.repository.ResetPasswordRepository;
import com.ifpe.br.odontosys.repository.UsuarioRepository;
import com.ifpe.br.odontosys.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RestController
@RequestMapping("esqueci-senha")
public class ResetPasswordController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final SecureRandom random = new SecureRandom();

    @PostMapping("/enviar-email")
    public ResponseEntity<?> solicitarRedefinicaoSenha(@RequestBody EmailRedefinicaoDeSenhaRequestDTO request) {
        UsuarioModel usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario == null) {
            return ResponseEntity.badRequest().body("E-mail não encontrado");
        }

        String codigo = String.valueOf(100000 + random.nextInt(900000));
        var entity = new ResetPasswordModel(codigo, usuario);

        resetPasswordRepository.save(entity);

        mailService.enviarEmailRedefinicaoSenha(usuario.getEmail(), codigo);

        return ResponseEntity.ok("E-mail de redefinição enviado, cheque sua caixa de SPAM.");
    }


    @PostMapping("/redefinir-senha")
    public ResponseEntity<?> redefinirSenha(@RequestBody RedefinirSenhaRequestDTO request) {

        var resetcodigo = resetPasswordRepository.findByCodigo(request.getCodigo());
        if (resetcodigo == null || resetcodigo.getExpirationDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("codigo inválido ou expirado.");
        }

        UsuarioModel usuario = resetcodigo.getUsuario();
        usuario.setSenha(encoder.encode(request.getNovaSenha()));
        usuarioRepository.save(usuario);

        resetPasswordRepository.delete(resetcodigo);

        return ResponseEntity.ok("Senha redefinida com sucesso!");
    }
}
