package br.ifpe.proext.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException( ) {

        super("A senha deve possuir no mínimo 6 caracteres.");
    }
}
