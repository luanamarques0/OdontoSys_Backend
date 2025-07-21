package com.ifpe.br.odontosys.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifpe.br.odontosys.DTO.request.ConsultaCadastroRequestDTO;
import com.ifpe.br.odontosys.DTO.request.ConsultaEdicaoRequestDTO;
import com.ifpe.br.odontosys.model.ConsultaModel;
import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.model.enums.StatusConsulta;
import com.ifpe.br.odontosys.repository.ConsultaRepository;
import com.ifpe.br.odontosys.repository.DentistaRepository;
import com.ifpe.br.odontosys.repository.DiasAtendimentoRepository;
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

    @Autowired
    private DiasAtendimentoRepository diasAtendimentoRepository;


    @Transactional
    public ConsultaModel saveConsulta(ConsultaCadastroRequestDTO consulta) {

        if(consulta.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Data da consulta não pode ser no passado");
        }

        DentistaModel dentista = dentistaRepository.findByCro(consulta.getDentistaCro())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        
        PacienteModel paciente = pacienteRepository.findByUsuarioId(consulta.getPacienteId())
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
                .motivoConsulta(consulta.getMotivo())
                .statusConsulta(StatusConsulta.AGENDADA)
                .build();

        var consultaSalva = consultaRepository.save(consultaModel);

        data.setDisponivel(false);

        return consultaSalva;
    }

    public List<ConsultaModel> findConsultaByDentistaAndData(Long dentistaId, LocalDate dataConsulta) {
        LocalDateTime inicioDoDia = dataConsulta.atStartOfDay();
        LocalDateTime fimDoDia = dataConsulta.atTime(23, 59, 59);

        return consultaRepository.findByDentistaUsuarioIdAndDataConsultaBetween(dentistaId, inicioDoDia, fimDoDia);
    }

    public List<ConsultaModel> findConsultasDoUsuario(Long usuarioId) {
        List<ConsultaModel> consultas = consultaRepository.findByPacienteUsuarioId(usuarioId);
        return consultas;
    }

    @Transactional
    public ConsultaModel updateConsulta(Long consultaId, ConsultaEdicaoRequestDTO consultaEdicao) {
        ConsultaModel consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + consultaId));

        // Atualizar apenas os campos permitidos
        if (consultaEdicao.getAvaliacao() != null) {
            consulta.setAvaliacao(consultaEdicao.getAvaliacao());
        }
        
        if (consultaEdicao.getProcedimentosRealizados() != null) {
            consulta.setProcedimentosRealizados(consultaEdicao.getProcedimentosRealizados());
        }
        
        if (consultaEdicao.getRecomendacoes() != null) {
            consulta.setRecomendacoes(consultaEdicao.getRecomendacoes());
        }
        
        if (consultaEdicao.getVoltaEsperada() != null) {
            consulta.setVoltaEsperada(consultaEdicao.getVoltaEsperada());
        }

        consulta.setStatusConsulta(StatusConsulta.REALIZADA);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void cancelarConsulta(Long consultaId) {
        ConsultaModel consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + consultaId));
        
        DiasAtendimentoModel dia = consulta.getDentista().getDiasAtendimento()
                .stream()
                .filter(d -> d.getDataAtendimento().equals(consulta.getDataConsulta()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dia de atendimento não encontrado"));

        dia.setDisponivel(true);
        consulta.setStatusConsulta(StatusConsulta.CANCELADA);

        diasAtendimentoRepository.save(dia);
        consultaRepository.save(consulta);
    }
}
