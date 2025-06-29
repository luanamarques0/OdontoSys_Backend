package com.ifpe.br.odontosys.service;

import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.model.UsuarioModel;
import com.ifpe.br.odontosys.repository.PacienteRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PacienteModel save(PacienteModel paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<PacienteModel> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Transactional
    public void update(Long id, PacienteModel pacienteAlterado) {
        PacienteModel pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        if (pacienteAlterado.getNome() != null && !pacienteAlterado.getNome().isBlank())
            pacienteExistente.setNome(pacienteAlterado.getNome());
        if (pacienteAlterado.getDataNascimento() != null)
            pacienteExistente.setDataNascimento(pacienteAlterado.getDataNascimento());
        if (pacienteAlterado.getTelefone() != null && !pacienteAlterado.getTelefone().isBlank())
            pacienteExistente.setTelefone(pacienteAlterado.getTelefone());

        UsuarioModel usuario = pacienteAlterado.getUsuario();
        if (usuario != null) {
            if (usuario.getEmail() != null && !usuario.getEmail().isBlank())
                pacienteExistente.getUsuario().setEmail(usuario.getEmail());
            if (usuario.getSenha() != null && !usuario.getSenha().isBlank())
                pacienteExistente.getUsuario().setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        pacienteRepository.save(pacienteExistente);

    }

}
