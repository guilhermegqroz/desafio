package com.guilherme.projectportfolioapi.enums;

public enum StatusProjeto {

    EM_ANALISE,
    ANALISE_REALIZADA,
    ANALISE_APROVADA,
    INICIADO,
    PLANEJADO,
    EM_ANDAMENTO,
    ENCERRADO,
    CANCELADO;

    public boolean podeTransicionarPara(StatusProjeto novoStatus) {

        if (novoStatus == CANCELADO) {
            return true;
        }

        switch (this) {

            case EM_ANALISE:
                return novoStatus == ANALISE_REALIZADA;
            case ANALISE_REALIZADA:
                return novoStatus == ANALISE_APROVADA;
            case ANALISE_APROVADA:
                return novoStatus == INICIADO;
            case INICIADO:
                return novoStatus == PLANEJADO;
            case PLANEJADO:
                return novoStatus == EM_ANDAMENTO;
            case EM_ANDAMENTO:
                return novoStatus == ENCERRADO;
            default:
                return false;
        }
    }
}