package com.ifpe.br.odontosys.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ifpe.br.odontosys.model.DentistaModel;

public interface DentistaRepository extends JpaRepository<DentistaModel, Long>{
    Optional<DentistaModel> findByCro(String cro);

}
