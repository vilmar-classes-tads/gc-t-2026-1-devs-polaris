package br.ifpe.proext.exception;

public class EditalNaoEncontradoException extends RuntimeException {
    public EditalNaoEncontradoException() {
        super("Edital não encontrado no sistema.");
    }
}