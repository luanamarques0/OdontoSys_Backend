package com.ifpe.br.odontosys.repository;

import com.ifpe.br.odontosys.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
}
