package br.ifpe.proext.exception;

public class PeriodoAvaliacaoInvalidoException extends RuntimeException {

    public PeriodoAvaliacaoInvalidoException() {
        super("O período de avaliação é inválido.");
    }

    public PeriodoAvaliacaoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
