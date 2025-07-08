package com.ifpe.br.odontosys.service;

import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import com.ifpe.br.odontosys.repository.DentistaRepository;
import com.ifpe.br.odontosys.repository.DiasAtendimentoRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DiasAtendimentoService {

    @Autowired
    private DiasAtendimentoRepository diasAtendimentoRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Transactional
    public void createDiasAtendimento(Long dentistaId, List<DiasAtendimentoModel> diasAtendimento) {
        DentistaModel dentista = dentistaRepository.findById(dentistaId)
                .orElseThrow(() -> new NoSuchElementException("Dentista não encontrado"));

        diasAtendimento.forEach(da -> da.setDentista(dentista));

        diasAtendimentoRepository.saveAll(diasAtendimento);
    }

    @Transactional
    public void updateDiasAtendimento(Long dentistaId, List<DiasAtendimentoModel> novosDias) {
        DentistaModel dentista = dentistaRepository.findById(dentistaId)
                .orElseThrow(() -> new NoSuchElementException("Dentista não encontrado"));

        if (novosDias.isEmpty()) {
            return;
        }

        LocalDate dataFiltrada = novosDias.get(0).getDataAtendimento().toLocalDate();

        List<DiasAtendimentoModel> diasExistentes = diasAtendimentoRepository.findAll()
                .stream()
                .filter(dia -> dia.getDentista() != null &&
                        dia.getDentista().getId().equals(dentistaId) &&
                        dia.getDataAtendimento().toLocalDate().equals(dataFiltrada))
                .toList();

        Map<LocalDateTime, DiasAtendimentoModel> mapaExistentes = diasExistentes.stream()
                .collect(Collectors.toMap(DiasAtendimentoModel::getDataAtendimento, dia -> dia));

        for (DiasAtendimentoModel novo : novosDias) {
            novo.setDentista(dentista);

            DiasAtendimentoModel existente = mapaExistentes.get(novo.getDataAtendimento());

            if (existente != null) {
                if (Boolean.FALSE.equals(existente.getDisponivel())) {
                    continue;
                }
                existente.setDisponivel(true);
                diasAtendimentoRepository.save(existente);
            } else {
                novo.setDisponivel(true);
                diasAtendimentoRepository.save(novo);
            }
        }

        //Para desativar os antes disponiveis
        Set<LocalDateTime> novosHorarios = novosDias.stream()
                .map(DiasAtendimentoModel::getDataAtendimento)
                .collect(Collectors.toSet());

        diasExistentes.stream()
                .filter(d -> Boolean.TRUE.equals(d.getDisponivel()) &&
                        !novosHorarios.contains(d.getDataAtendimento()))
                .forEach(d -> {
                    d.setDisponivel(false);
                    diasAtendimentoRepository.save(d);
                });
    }

}
