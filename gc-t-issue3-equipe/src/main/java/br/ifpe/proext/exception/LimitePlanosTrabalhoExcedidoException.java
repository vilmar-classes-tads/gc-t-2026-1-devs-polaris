package br.ifpe.proext.exception;

public class LimitePlanosTrabalhoExcedidoException extends RuntimeException {

    public LimitePlanosTrabalhoExcedidoException() {
        super("O projeto já atingiu o limite máximo de 4 planos de trabalho.");
    }
}
