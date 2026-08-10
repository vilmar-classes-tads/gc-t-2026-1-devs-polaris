package br.ifpe.proext.exception;

public class PeriodoSubmissaoInvalidoException extends RuntimeException {

    public PeriodoSubmissaoInvalidoException() {
        super("A submissão inicial deve ter data menor ou igual à submissão final.");
    }
}