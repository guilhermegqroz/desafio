package com.guilherme.projectportfolioapi.enums;

import com.guilherme.projectportfolioapi.entity.Projeto;

public enum ClassificacaoRisco {
    BAIXO,
    MEDIO,
    ALTO;

    private ClassificacaoRisco calcularRisco(Projeto projeto) {
        return ClassificacaoRisco.BAIXO;
    }
}