package br.ifpe.proext.exception;

public class PeriodoAvaliacaoInvalidoException extends RuntimeException {

    public PeriodoAvaliacaoInvalidoException() {
        super("A avaliação inicial deve ter data menor ou igual à avaliação final.");
    }
}
