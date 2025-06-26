package com.ifpe.br.odontosys.repository;

import com.ifpe.br.odontosys.model.ResetPasswordModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordRepository extends JpaRepository<ResetPasswordModel, Long> {

    ResetPasswordModel findByCodigo(String codigo);

}
