package br.ifpe.proext.exception;

public class TermoNaoAceitoException extends RuntimeException {

    public TermoNaoAceitoException() {
        super("O projeto só pode ser submetido após o aceite do termo pelo coordenador.");
    }
}

