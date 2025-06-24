package com.ifpe.br.odontosys.service;

import com.ifpe.br.odontosys.model.DentistaModel;
import com.ifpe.br.odontosys.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    public DentistaModel save(DentistaModel dentista){
        return dentistaRepository.save(dentista);
    }

}
