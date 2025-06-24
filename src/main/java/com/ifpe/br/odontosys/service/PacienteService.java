package com.ifpe.br.odontosys.service;

import com.ifpe.br.odontosys.model.PacienteModel;
import com.ifpe.br.odontosys.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteModel save(PacienteModel paciente){
        return pacienteRepository.save(paciente);
    }

}
