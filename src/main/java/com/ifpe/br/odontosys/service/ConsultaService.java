package com.ifpe.br.odontosys.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpe.br.odontosys.DTO.request.ConsultaCadastroRequestDTO;
import com.ifpe.br.odontosys.model.ConsultaModel;
import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.repository.ConsultaRepository;
import com.ifpe.br.odontosys.repository.DentistaRepository;
import com.ifpe.br.odontosys.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;


    @Transactional
    public ConsultaModel saveConsulta(ConsultaCadastroRequestDTO consulta) {
        if(consulta.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Data da consulta não pode ser no passado");
        }

        DentistaModel dentista = dentistaRepository.findByCro(consulta.getDentistaCro())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        
        PacienteModel paciente = pacienteRepository.findById(consulta.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        DiasAtendimentoModel data = dentista.getDiasAtendimento()
                .stream()
                .filter(dia -> dia.getDataAtendimento().equals(consulta.getDataHora()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dentista não atende nesse dia"));

        if(!data.getDisponivel()) {
            throw new RuntimeException("Dentista não disponível nesse horário");
        }

        ConsultaModel consultaModel = ConsultaModel.builder()
                .dataConsulta(consulta.getDataHora())
                .endereco(dentista.getEndereco())
                .paciente(paciente)
                .dentista(dentista)
                .build();

        var consultaSalva = consultaRepository.save(consultaModel);

        data.setDisponivel(false);

        return consultaSalva;
    }
}
