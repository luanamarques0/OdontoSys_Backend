package com.ifpe.br.odontosys.service;

import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.model.EnderecoModel;
import com.ifpe.br.odontosys.model.UsuarioModel;
import com.ifpe.br.odontosys.repository.DentistaRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DentistaModel save(DentistaModel dentista) {
        return dentistaRepository.save(dentista);
    }

    public List<DentistaModel> listarTodos() {
        return dentistaRepository.findAll();
    }

    @Transactional
    public void update(Long id, DentistaModel dentistaAlterado) {
        DentistaModel dentistaExistente = dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        if (dentistaAlterado.getNome() != null && !dentistaAlterado.getNome().isBlank())
            dentistaExistente.setNome(dentistaAlterado.getNome());
        if (dentistaAlterado.getTelefone() != null && !dentistaAlterado.getTelefone().isBlank())
            dentistaExistente.setTelefone(dentistaAlterado.getTelefone());

        UsuarioModel usuario = dentistaAlterado.getUsuario();
        if (usuario != null) {
            if (usuario.getEmail() != null && !usuario.getEmail().isBlank())
                dentistaExistente.getUsuario().setEmail(usuario.getEmail());
            if (usuario.getSenha() != null && !usuario.getSenha().isBlank())
                dentistaExistente.getUsuario().setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        atualizarEndereco(dentistaExistente.getEndereco(), dentistaAlterado.getEndereco());

        dentistaRepository.save(dentistaExistente);
    }

    private void atualizarEndereco(EnderecoModel enderecoExistente, EnderecoModel enderecoNovo) {
        if (enderecoNovo == null || enderecoExistente == null)
            return;

        if (enderecoNovo.getCidade() != null && !enderecoNovo.getCidade().isBlank())
            enderecoExistente.setCidade(enderecoNovo.getCidade());
            if (enderecoNovo.getBairro() != null && !enderecoNovo.getBairro().isBlank())
            enderecoExistente.setBairro(enderecoNovo.getBairro());
        if (enderecoNovo.getNumero() != null && !enderecoNovo.getNumero().isBlank())
            enderecoExistente.setNumero(enderecoNovo.getNumero());
        if (enderecoNovo.getCep() != null && !enderecoNovo.getCep().isBlank())
            enderecoExistente.setCep(enderecoNovo.getCep());
    }

}
