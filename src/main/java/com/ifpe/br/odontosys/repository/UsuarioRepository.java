package com.ifpe.br.odontosys.repository;

import com.ifpe.br.odontosys.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> { }
