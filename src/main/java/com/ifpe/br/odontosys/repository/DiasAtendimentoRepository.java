package com.ifpe.br.odontosys.repository;

import com.ifpe.br.odontosys.model.DiasAtendimentoModel;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiasAtendimentoRepository extends JpaRepository<DiasAtendimentoModel, Long> {
    List<DiasAtendimentoModel> findByDentistaCroAndDisponivelTrue(String dentistaCro);

    boolean existsByDentistaIdAndDataAtendimento(Long dentistaId, LocalDateTime dataAtendimento);
}
