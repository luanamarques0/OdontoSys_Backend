package com.ifpe.br.odontosys.repository;

import com.ifpe.br.odontosys.model.ConsultaModel;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {

    List<ConsultaModel> findByDentistaIdAndDataConsultaBetween(Long dentistaId, LocalDateTime dataInicio, LocalDateTime dataFim);
    List<ConsultaModel> findByPacienteId(Long pacienteId);

}
