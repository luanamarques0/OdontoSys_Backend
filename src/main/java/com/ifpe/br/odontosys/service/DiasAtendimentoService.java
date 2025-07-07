package com.ifpe.br.odontosys.service;

import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.model.DiasAtendimentoModel;
import com.ifpe.br.odontosys.repository.DentistaRepository;
import com.ifpe.br.odontosys.repository.DiasAtendimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiasAtendimentoService {

    @Autowired
    private DiasAtendimentoRepository diasAtendimentoRepository;

    @Autowired
    private DentistaRepository dentistaRepository;


    @Transactional
    public void createDiasAtendimento(Long dentistaId, List<DiasAtendimentoModel> diasAtendimento){
        DentistaModel dentista = dentistaRepository.findById(dentistaId)
                .orElseThrow(() -> new NoSuchElementException("Dentista não encontrado"));

        diasAtendimento.forEach(da -> da.setDentista(dentista));

        diasAtendimentoRepository.saveAll(diasAtendimento);
    }


}
