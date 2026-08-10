package br.ifpe.proext.exception;

public class ProjetoJaSubmetidoException extends RuntimeException {

    public ProjetoJaSubmetidoException() {
        super("Projeto já submetido.");
    }
}

