package com.ifpe.br.odontosys.model.enums;

public enum StatusConsulta {
    AGENDADA(1),
    REALIZADA(2),
    CANCELADA(3);

    private final int codigo;

    StatusConsulta(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static StatusConsulta fromCodigo(int codigo) {
        for (StatusConsulta status : StatusConsulta.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido para StatusConsulta: " + codigo);
    }
}
