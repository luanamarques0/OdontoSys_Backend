package com.ifpe.br.odontosys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_redefinicao_de_senha")
public class ResetPasswordModel extends BusinessModel{


    private static final Long EXPIRATION = 5L;

    private String codigo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private UsuarioModel usuario;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    public ResetPasswordModel(String codigo, UsuarioModel usuario) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.expirationDate = calcularDataExpiracao(EXPIRATION);
    }

    private LocalDateTime calcularDataExpiracao(Long tempoMinutos) {
        return LocalDateTime.now().plusMinutes(tempoMinutos);
    }

}
